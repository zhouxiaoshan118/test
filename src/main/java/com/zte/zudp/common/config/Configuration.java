package com.zte.zudp.common.config;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-23.
 */
public final class Configuration {

    private static Properties properties;

    static {
        properties = ConfigurationLoader.loadConfiguration();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static Object get(Object key) {
        return properties.get(key);
    }

    public static Object put(Object key, Object value) {
        return properties.put(key, value);
    }

    public static void replace(Object key, Object value) {
        properties.remove(key);
        properties.put(key, value);
        // properties.replace(key, value);
    }

    public static boolean containsKey(Object key) {
        return properties.containsKey(key);
    }

    public static void store(Writer writer, String comment) {
        try {
            properties.store(writer, comment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void store(OutputStream outputStream, String comment) {
        try {
            properties.store(outputStream, comment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> map() {
        Map<String, Object> result = new HashMap<>();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();

        for (Map.Entry<Object, Object> entry : entries) {
            result.put(entry.getKey().toString(), entry.getValue());
        }

        return result;
    }
}
