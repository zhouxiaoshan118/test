package com.zte.zudp.common.persistence.entity;

/**
 * 可容下 错误信息 实体类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-23.
 */
public abstract class ErrorEntity extends AbstractEntity {

    // @JsonView(View.Entity.class)
    private String error;

    public ErrorEntity() {
    }

    public ErrorEntity(String id) {
        super(id);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
