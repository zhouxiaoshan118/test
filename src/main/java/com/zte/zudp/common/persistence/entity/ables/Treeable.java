package com.zte.zudp.common.persistence.entity.ables;

import java.io.Serializable;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public interface Treeable<T, ID extends Serializable> extends Weightable<T> {

    String ID_SEPARATOR = ",";
    String DEFAULT_ROOT_ICON = "root";
    String DEFAULT_BRANCH_ICON = "branch";
    String DEFAULT_LEAF_ICON = "leaf";

    void setTreeName(String treeName);

    String getTreeName();

    /**
     * 显示的图标 大小为16×16
     */
    String getIcon();

    void setIcon(String icon);

    /**
     * 父路径
     */
    ID getParentId();

    void setParentId(ID parentId);

    /**
     * 所有父路径 如1,2,3,
     */
    String getParentIds();

    void setParentIds(String parentIds);

    /**
     * 获取 parentIds 之间的分隔符
     */
    String getSeparator();

    /**
     * 把自己构造出新的父节点路径
     */
    String makeSelfAsNewParentIds();

    /**
     * 是否是根节点
     */
    boolean isRoot();

    /**
     * 根节点默认图标 如果没有默认 空即可  大小为16×16
     */
    String getRootDefaultIcon();

    /**
     * 树枝节点默认图标 如果没有默认 空即可  大小为16×16
     */
    String getBranchDefaultIcon();

    /**
     * 树叶节点默认图标 如果没有默认 空即可  大小为16×16
     */
    String getLeafDefaultIcon();
}
