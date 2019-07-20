package com.zte.zudp;

import com.zte.zudp.common.listener.MenuAnnotationListener;
import com.zte.zudp.common.persistence.entity.MenuTree;
import com.zte.zudp.system.config.freemarker.FreemarkerView;
import com.zte.zudp.system.config.mybatis.MapperLoader;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 启动类
 *
 * @author piumnl
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.zte.zudp")
@RestController
@Configuration
public class ZudpApplication {

    @RequestMapping(value = "/tree")
    public MenuTree getTree(){
       return MenuAnnotationListener.getMenuTree();
    }

    public static void main(String[] args) {
        SpringApplication springApplication =new SpringApplication(ZudpApplication.class);
        springApplication.addListeners(new MenuAnnotationListener());
        springApplication.run(args);
    }

    /**
     * 增加自定义视图变量和方法
     */
    @Bean
    public CommandLineRunner customFreemarker(final FreeMarkerViewResolver resolver) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                // 增加视图
                resolver.setViewClass(FreemarkerView.class);
                // 添加自定义解析器
                // Map<String, Object> map = resolver.getAttributesMap();
                // map.put("conver", new MyConver());
            }
        };
    }

    @Bean
    public MapperLoader mapperLoader() {
        return new MapperLoader("mappers/", 5, 5);
    }
    
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //文件最大  
        factory.setMaxFileSize("100MB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("500MB");  
        return factory.createMultipartConfig();  
    }  
}
