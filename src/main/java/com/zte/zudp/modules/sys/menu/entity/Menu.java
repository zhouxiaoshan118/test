package com.zte.zudp.modules.sys.menu.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.entity.TreeEntity;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.utils.enums.URLTarget;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * 菜单实体类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public class Menu extends TreeEntity<Menu> {

    /* 链接地址 */
    @JsonView(View.Default.class)
    private String href;

    /* 链接打开方式 */
    @JsonView(View.Default.class)
    private URLTarget target;

    /* 是否激活可用 */
    @JsonView(View.Default.class)
    private Boolean actived;

    /* 权限标识 */
    @JsonView(View.Default.class)
    private String permission;

    @JsonView(View.Default.class)
    private Boolean hide;

    public Menu() {
    }

    public Menu(String id) {
        this.setId(id);
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public URLTarget getTarget() {
        return target;
    }

    public void setTarget(URLTarget target) {
        this.target = target;
    }

    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    @Override
    public boolean isRoot() {
        return !StringUtils.isEmpty(getParentIds());
    }


}
