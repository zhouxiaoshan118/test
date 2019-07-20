package com.zte.zudp.modules.sys.role.entity;

import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.modules.sys.menu.entity.Menu;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * 角色实体类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public class Role extends DateEntity {

    private static final long serialVersionUID = 1L;

    /* 前台显示名称 */
    @JsonView(View.Default.class)
    @NotEmpty
    @Length(min = 3, max = 32)
    private String name;

    /* 系统验证时的标识 */
    @JsonView(View.Default.class)
    @NotEmpty
    @Length(min = 3, max = 32)
    private String role;

    @JsonView(View.Default.class)
    @NotEmpty
    private String description;

    @JsonView(View.Default.class)
    @NotEmpty
    private Boolean actived;

    private Set<Menu> permissions;

    public Role() {
    }

    public Role(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public Set<Menu> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Menu> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role r = (Role) o;

        return role != null ? role.equals(r.role) : r.role == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
