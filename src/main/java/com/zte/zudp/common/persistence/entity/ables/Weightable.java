package com.zte.zudp.common.persistence.entity.ables;

/**
 * 实体实现该接口表示想要调整数据的顺序，小在前，大在后。
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public interface Weightable<T> extends Comparable<T> {

    /**
     * 权重 用于排序 越小越排在前边
     *
     * @return 权重值
     */
    Integer getWeight();

    void setWeight(Integer weight);
}
