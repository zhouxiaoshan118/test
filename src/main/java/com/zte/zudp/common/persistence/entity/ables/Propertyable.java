package com.zte.zudp.common.persistence.entity.ables;

/**
 * 实现此类可以将对象转为键值对，多用于返回前台时使用
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-12.
 */
public interface Propertyable {

    String key();

    Object value();
}
