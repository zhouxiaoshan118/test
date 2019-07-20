package com.zte.zudp.common.persistence.dao;

import java.util.List;

import com.zte.zudp.common.persistence.entity.AbstractEntity;

/**
 * <p>Dao 接口的补充</p>
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public interface AbstractDao<T extends AbstractEntity> extends BaseDao<T> {

    void batchDelete(String[] ids);

    List<T> findList(T searchable);

    long count(T searchable);
}
