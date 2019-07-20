package com.zte.zudp.common.persistence.entity.ables;

/**
 * 如果实体类有对应的数据库表，则需要实现此接口
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public interface Tableable {

    /**
     * 获取实体的表名
     *
     * @return
     */
    String getTable();
}
