package com.zte.zudp.common.persistence.dao;

import com.zte.zudp.common.persistence.entity.BaseEntity;

import java.util.List;

/**
 * <p>Dao 接口</p>
 * <p>定义了一些作为 Dao 的最基本元素</p>
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public interface BaseDao<T extends BaseEntity> {

    int insert(T t);
    
    int saveInteraction(T t);

    void delete(String id);

     int update(T t);

    T get(String id);

    List<T> findList();

}
