package com.zte.zudp.system.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zte.zudp.common.utils.SpringUtils;
import com.zte.zudp.common.config.Constants;
import com.zte.zudp.system.config.shiro.cache.SpringCacheManagerWrapper;

/**
 * 缓存处理工具类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-14.
 */
public class CacheUtils {

    private static Logger logger = LoggerFactory.getLogger(Constants.INFO_LOG);

    private static CacheManager cacheManager = SpringUtils.getBean(SpringCacheManagerWrapper.class);

    public static final String USER_CACHE = CacheType.USER.getName();

    public static final String SYS_CACHE = CacheType.SYS.getName();

    /**
     * 系统默认缓存中 CacheType.SYS 的其中一个 key
     */
    public static final String DICT_MAP = "dictMap";

    /**
     * 获取系统缓存中键为 key 的值
     *
     * @param key 系统缓存的 key 值
     * @return .
     */
    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * 获取指定缓存中键为 key 的值
     *
     * @param cacheName 指定的缓存名
     * @param key       缓存的键
     * @return .
     */
    public static Object get(String cacheName, String key) {
        return getCache(cacheName).get(key);
    }

    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * 将指定的 键 和 值 写入到指定的缓存中
     *
     * @param cacheName 指定缓存的name值
     * @param key       键
     * @param value     值
     */
    public static void put(String cacheName, String key, Object value) {
        getCache(cacheName).put(key, value);
    }

    /**
     * 移除系统缓存下指定的键值对
     *
     * @param key 要移除的键名
     */
    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * 移除指定缓存下指定的键值对
     *
     * @param cacheName 指定缓存名
     * @param key       要移除的键名
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 清空指定缓存
     *
     * @param cacheName 指定缓存名
     */
    public static void clear(String cacheName) {
        Cache<String, Object> cache = getCache(cacheName);
        cache.clear();
        logger.info("clear cache for {}", cacheName);
    }

    /**
     * 获取指定缓存名的缓存
     *
     * @param cacheName 缓存名
     * @return 如果存在该缓存则返回，否则抛出异常
     */
    public static Cache<String, Object> getCache(String cacheName) {
        Cache<String, Object> cache = cacheManager.getCache(cacheName);

        if (cache == null) {
            logger.warn("no cache named '{}' in the system.", cacheName);
            throw new RuntimeException("no cache named '" + cacheName + "' in the system.");
        }

        return cache;
    }

    public static Cache<String, Object> getCache(CacheType cacheType) {
        return getCache(cacheType.getName());
    }
}
