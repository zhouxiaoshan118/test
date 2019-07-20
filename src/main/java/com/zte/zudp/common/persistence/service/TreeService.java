package com.zte.zudp.common.persistence.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.persistence.dao.TreeDao;
import com.zte.zudp.common.persistence.entity.TreeEntity;
import com.zte.zudp.common.utils.StringUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-11.
 */
@Transactional(readOnly = true)
public abstract class TreeService<T extends TreeEntity> extends BusinessService<T> {

    private TreeDao<T> dao() {
        return (TreeDao<T>) dao;
    }

    @Override
    protected void pre(T tree) {
        T parent;
        String parentId = tree.getParentId();

        if (StringUtils.isNotEmpty(parentId)) {
            parent = get(parentId);
            if (parent != null) {
                tree.setParentIds(parent.makeSelfAsNewParentIds());
            }
        }

        super.pre(tree);
    }

    @Override
    @Transactional
    public void preUpdate(T menu) {
        super.preUpdate(menu);

        // 获取当前修改菜单的源数据
        T origin = get(menu.getId());

        // 查找当前菜单的所有子菜单
        String originParentIds = origin.makeSelfAsNewParentIds();
        List<T> children = dao().findChildren(originParentIds);

        if (children != null && children.size() > 0) {
            T parent = get(menu.getParentId());

            // 获取修改后的菜单父节点
            String destParentIds = menu.makeSelfAsNewParentIds();
            if (parent != null) {
                destParentIds = parent.makeSelfAsNewParentIds() + "," + menu.getId();
            }

            if (StringUtils.equals(destParentIds, originParentIds)) {       // 目标相同则不更新
                return;
            }

            for (T child : children) {
                child.setParentId(originParentIds);  // 需要修改的数据
                child.setParentIds(destParentIds);
                child.setUpdateDate(menu.getUpdateDate());
                // child.setUpdateUser(menu.getUpdateUser());
            }

            dao().updateChildren(children);
        }
    }
}
