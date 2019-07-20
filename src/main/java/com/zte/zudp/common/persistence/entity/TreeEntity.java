package com.zte.zudp.common.persistence.entity;

import java.util.Objects;

import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.common.persistence.entity.ables.Treeable;

/**
 * 树形 实体类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public abstract class TreeEntity<T> extends DateEntity implements Treeable<TreeEntity, String> {

    // @JsonView(View.Tree.class)
    private String treeName;

    // @JsonView(View.Tree.class)
    private String icon;

    // @JsonView(View.Tree.class)
    private String parentId;

    // @JsonView(View.Tree.class)
    private T parent;

    // @JsonView(View.Tree.class)
    private String parentIds;

    // @JsonView(View.Tree.class)
    private Integer weight;

    public TreeEntity() {
        super();
    }

    public TreeEntity(String id) {
        super(id);
    }

    @Override
    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String getTreeName() {
        return this.treeName;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String getParentId() {
        return this.parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getParentIds() {
        return this.parentIds;
    }

    @Override
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Override
    public String getSeparator() {
        return ID_SEPARATOR;
    }

    @Override
    public boolean isRoot() {
        return Objects.equals(getParentId(), "");
    }

    @Override
    public String getRootDefaultIcon() {
        return DEFAULT_ROOT_ICON;
    }

    @Override
    public String getBranchDefaultIcon() {
        return DEFAULT_BRANCH_ICON;
    }

    @Override
    public String getLeafDefaultIcon() {
        return DEFAULT_LEAF_ICON;
    }

    @Override
    public Integer getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    @Override
    public String makeSelfAsNewParentIds() {
        String parentIds = getParentIds();
        if (StringUtils.isNotEmpty(parentIds)) {
            return parentIds + getSeparator() + getId();
        } else {
            return "" + getSeparator() + getId();
        }
    }

    public String root() {
        if (isRoot()) {
            return this.getId();
        }

        return this.getParentIds().split(getSeparator())[0];
    }

    @Override
    public int compareTo(TreeEntity entity) {
        Integer other = entity.getWeight();
        Integer weight = this.getWeight();

        if (weight.equals(other)) {
            return 0;
        }

        return weight > other ? 1 : -1;
    }
}
