package com.zte.zudp.common.persistence.service;

import java.util.List;

import com.zte.zudp.common.persistence.entity.BaseEntity;

/**
 * <p>Service层的接口类型</p>
 * 基本的增删改查
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 插入
     * @param t 要插入的数据
     * @return 数据库返回的影响行数
     */
    int insert(T t);

    /**
     * 删除
     * @param id 要删除的id
     */
    void delete(String id);

    /**
     * 更新
     * @param t 要更新的数据
     * @return 数据库返回的影响行数
     */
    int update(T t);

    /**
     * 根据 id 查询数据
     * @param id 数据的id直
     * @return 数据
     */
    T get(String id);

    /**
     * 查询所有
     * @return 查询所有
     */
    List<T> findList();

}
