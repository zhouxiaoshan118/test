package com.zte.zudp.common.config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import com.zte.zudp.common.exception.BaseException;

/**
 * 此处处理 sbom.properties 文件，包括启动加载和关闭保存操作
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-15.
 */
public class ConfigurationLoader implements DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(Constants.SYSTEM_LOG);

    private static Properties properties;

    private static String location = "/application.properties";

    public static Properties loadConfiguration() {
        logger.info("loading system config file...");

        properties = new Properties();
        InputStream inputStream = ConfigurationLoader.class.getResourceAsStream(location);
        try {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new BaseException("common", "load config file failed!");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("saving system config file...");

        String path = ConfigurationLoader.class.getResource("/").getPath() + location;
        Path configFilePath = Paths.get(path).normalize();

        if (Files.notExists(configFilePath)) {
            Files.createFile(configFilePath);
        }

        FileWriter writer = new FileWriter(configFilePath.toFile());
        Configuration.store(writer, "");
    }
}
