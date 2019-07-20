package com.zte.zudp.common.persistence.entity;

import com.zte.zudp.common.utils.IDUtils;

/**
 * 关联表对象实体类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-21.
 */
public class AssociationEntity implements BaseEntity {

    private String id;

    private String one;

    private String other;

    public AssociationEntity(String one, String other) {
        this.id = IDUtils.defaultUUID();
        this.one = one;
        this.other = other;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
