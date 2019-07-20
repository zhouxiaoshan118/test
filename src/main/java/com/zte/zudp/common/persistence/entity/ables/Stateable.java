package com.zte.zudp.common.persistence.entity.ables;

/**
 * 实体实现该接口，表示需要进行状态管理
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public interface Stateable<T extends Enum<? extends Stateable.Status>> {

    void setStatus(T status);

    T getStatus();

    interface Status {
        String getInfo();
    }
}
