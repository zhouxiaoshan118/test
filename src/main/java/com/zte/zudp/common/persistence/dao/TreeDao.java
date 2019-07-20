package com.zte.zudp.common.persistence.dao;

import java.util.List;

import com.zte.zudp.common.persistence.entity.TreeEntity;

/**
 * <p>Dao 接口的补充</p>
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public interface TreeDao<T extends TreeEntity> extends AbstractDao<T> {

    void updateChildren(List<T> children);

    List<T> findChildren(String parentIds);
}
