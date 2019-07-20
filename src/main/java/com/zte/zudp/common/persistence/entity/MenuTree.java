package com.zte.zudp.common.persistence.entity;

import java.util.List;

/**
 * MenuTree, Tree结构
 * Created by DW on 2017/12/11 14:31
 */

public class MenuTree {

//    private Menu menu;

    private String id;
    private String name;
    private Integer order;
    private String url;

    private List<MenuTree> children;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }
}
