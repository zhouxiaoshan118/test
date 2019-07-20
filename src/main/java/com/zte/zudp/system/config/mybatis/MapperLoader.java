package com.zte.zudp.system.config.mybatis;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.zte.zudp.common.config.Constants;

/**
 * 监听 MyBatis 的 mapper 文件是否有过修改，如果有则自动加载最新的文件
 * @author piumnl
 */
public final class MapperLoader implements DisposableBean, ApplicationContextAware {

    private transient Logger logger = LoggerFactory.getLogger(Constants.INFO_LOG);

    private transient String scannPath;

    private int initialDelay = 5;

    private int period = 5;

    private ConfigurableApplicationContext context = null;

    private HashMap<String, String> fileMapping = new HashMap<>();

    private ScheduledExecutorService service = null;

    public MapperLoader(String scannPath, int initialDelay, int period) {
        this.scannPath = scannPath;
        this.initialDelay = initialDelay;
        this.period = period;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = (ConfigurableApplicationContext) applicationContext;

        // 防止 context 为 null
        init();
    }

    public void init() {
        service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Task(), initialDelay, period, TimeUnit.SECONDS);

        logger.info("loaded task timing for dynamic load mybatis modified map file.");
    }

    @Override
    public void destroy() throws Exception {
        if (service != null) {
            service.shutdownNow();
        }
    }

    class Task implements Runnable {
        private Scanner scanner = null;

        Task() {
            try {
                // 触发文件监听事件
                scanner = new Scanner();
                scanner.scan();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                if (scanner.isChanged()) {
                    logger.info("reloading changed mybatis map file.");
                    scanner.reloadXML();
                    logger.info("mybatis map file loading is complete.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings({"rawtypes"})
    class Scanner {

        private String[] basePackages;
        private static final String XML_RESOURCE_PATTERN = "**/*.xml";
        private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

        public Scanner() {
            basePackages = StringUtils.tokenizeToStringArray(MapperLoader.this.scannPath,
                    ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        }

        public Resource[] getResource(String basePackage, String pattern) throws IOException {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath(context.getEnvironment().resolveRequiredPlaceholders(
                    basePackage)) + "/" + pattern;
            return resourcePatternResolver.getResources(packageSearchPath);
        }

        public void reloadXML() throws Exception {
            SqlSessionFactory factory = context.getBean(SqlSessionFactory.class);
            Configuration configuration = factory.getConfiguration();
            // 移除加载项
            removeConfig(configuration);
            // 重新扫描加载
            for (String basePackage : basePackages) {
                Resource[] resources = getResource(basePackage, XML_RESOURCE_PATTERN);
                if (resources != null) {
                    for (Resource resource : resources) {
                        if (resource != null) {
                            try {
                                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(),
                                        configuration, resource.toString(), configuration.getSqlFragments());
                                xmlMapperBuilder.parse();
                            } catch (Exception e) {
                                throw new NestedIOException("Failed to parse mapping resource: '" + resource + "'", e);
                            } finally {
                                ErrorContext.instance().reset();
                            }
                        }
                    }
                }
            }

        }

        private void removeConfig(Configuration configuration) throws Exception {
            Class<?> classConfig = configuration.getClass();
            clearMap(classConfig, configuration, "mappedStatements");
            clearMap(classConfig, configuration, "caches");
            clearMap(classConfig, configuration, "resultMaps");
            clearMap(classConfig, configuration, "parameterMaps");
            clearMap(classConfig, configuration, "keyGenerators");
            clearMap(classConfig, configuration, "sqlFragments");

            clearSet(classConfig, configuration, "loadedResources");

        }

        private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
            Field field = classConfig.getDeclaredField(fieldName);
            field.setAccessible(true);
            Map mapConfig = (Map) field.get(configuration);
            mapConfig.clear();
        }

        private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
            Field field = classConfig.getDeclaredField(fieldName);
            field.setAccessible(true);
            Set setConfig = (Set) field.get(configuration);
            setConfig.clear();
        }

        public void scan() throws IOException {
            if (!fileMapping.isEmpty()) {
                return;
            }
            for (String basePackage : basePackages) {
                Resource[] resources = getResource(basePackage, XML_RESOURCE_PATTERN);
                if (resources != null) {
                    for (Resource resource : resources) {
                        String multi_key = getValue(resource);
                        fileMapping.put(resource.getFilename(), multi_key);
                    }
                }
            }
        }

        private String getValue(Resource resource) throws IOException {
            String contentLength = String.valueOf((resource.contentLength()));
            String lastModified = String.valueOf((resource.lastModified()));
            return contentLength + lastModified;
        }

        public boolean isChanged() throws IOException {
            boolean isChanged = false;
            for (String basePackage : basePackages) {
                Resource[] resources = getResource(basePackage, XML_RESOURCE_PATTERN);
                if (resources != null) {
                    for (Resource resource : resources) {
                        String name = resource.getFilename();
                        String value = fileMapping.get(name);
                        String multi_key = getValue(resource);
                        if (!multi_key.equals(value)) {
                            isChanged = true;
                            fileMapping.put(name, multi_key);
                        }
                    }
                }
            }
            return isChanged;
        }
    }
}
