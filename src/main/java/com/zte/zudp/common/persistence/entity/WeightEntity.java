package com.zte.zudp.common.persistence.entity;

import com.zte.zudp.common.persistence.entity.ables.Weightable;

/**
 * 拥有权重属性的实体类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public abstract class WeightEntity extends AbstractEntity implements Weightable {

    private Integer weight;

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
