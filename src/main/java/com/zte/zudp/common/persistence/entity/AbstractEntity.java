package com.zte.zudp.common.persistence.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.entity.ables.Validatable;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * <p>实体接口的抽象类</p>
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public abstract class AbstractEntity implements BaseEntity, Serializable, Validatable {

    @JsonView(View.Entity.class)
    private String id;

    /**
     * 删除标识位无需序列化
     */
    private Boolean del = Boolean.FALSE;

    public AbstractEntity() {
        del = Boolean.FALSE;
    }

    public AbstractEntity(String id) {
        super();
        this.id = id;
    }

    @JsonIgnore()
    public boolean isNewRecord() {
        return getId() == null || "".equals(getId());
    }

    // -----------------------------------------------------------------------------------------------

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDel() {
        return this.del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    /**
     * (non-Javadoc)
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) obj;

        return this.getId() != null && this.getId().equals(that.getId());
    }

    /**
     * (non-Javadoc)
     *
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 17 + (getId() == null ? 0 : getId().hashCode() * 31);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
