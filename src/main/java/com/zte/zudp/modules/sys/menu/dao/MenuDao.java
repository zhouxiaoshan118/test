package com.zte.zudp.modules.sys.menu.dao;

import java.util.List;

import com.zte.zudp.common.persistence.dao.TreeDao;
import com.zte.zudp.modules.sys.menu.entity.Menu;

/**
 * 菜单的DAO
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public interface MenuDao extends TreeDao<Menu> {

    /**
     * 通过账户id查找其下面所有可访问的子节点菜单
     *
     * @param userId 账户id
     * @return 所有可访问的子节点菜单
     */
    List<Menu> findAccountMenu(String userId);

    /**
     * 查找属于角色的菜单
     * @param menuRole
     * @return
     */
    List<Menu> findRoleMenu(String menuRole);

    /**
     * 查找属于角色的菜单，包含隐藏不可见的
     * @param role
     * @return
     */
    List<Menu> findRoleMenuAndHide(String role);

    /**
     * 查找指定菜单下的所有子节点
     * @param menu
     * @return
     */
    List<Menu> findChildren(Menu menu);

    void updateChildren(List<Menu> update);
    
    /**
     * 
    * @Title: delChilds 
    * @Description: 删除菜单本身和字菜单
    * @param @param id    设定文件 
    * @return void    返回类型 
    * @throws
     */
    void delChilds(String id);
}
