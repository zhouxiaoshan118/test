package com.zte.zudp.common.persistence.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public abstract class DateEntity extends ErrorEntity {

    @JsonView(View.User.class)
    private User createUser;

    @JsonView(View.Date.class)
    private Date createDate;

    @JsonView(View.User.class)
    private User updateUser;

    @JsonView(View.Date.class)
    private Date updateDate;

    public DateEntity() {
        super();
    }

    public DateEntity(String id) {
        super(id);
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
