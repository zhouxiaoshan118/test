package com.zte.zudp.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-11-14.
 */
@Repository
@CacheConfig(cacheNames = "ehcacheTest")
public class EhCacheDao {

    @Cacheable
    public String save(String typeId) {
        System.out.println("save()执行了=============");
        return typeId;
    }

    @CachePut
    public String update(String typeId) {
        System.out.println("update()执行了=============");
        return typeId;
    }

    @CacheEvict
    public String delete(String typeId) {
        System.out.println("delete()执行了=============");
        return typeId;
    }

    @Cacheable
    public String select(String typeId) {
        System.out.println("select()执行了=============");
        return typeId;
    }
}
