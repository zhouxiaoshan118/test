package com.zte.zudp.modules.sys.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.persistence.service.TreeService;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.common.utils.JSONUtils;
import com.zte.zudp.modules.sys.menu.dao.MenuDao;
import com.zte.zudp.modules.sys.menu.entity.Menu;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.utils.UserUtils;

/**
 * 菜单的Service类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
@Service
@Transactional(readOnly = true)
public class MenuService extends TreeService<Menu> {

    private static final String MENU_ATTRIBUTE = "menu_list";

    private MenuDao dao() {
        return (MenuDao) dao;
    }

    /**
     * 返回一棵ZTree结构的树
     *
     * @return 返回一棵ZTree结构的树
     */
    public List<Map<String, Object>> tree() {
        Menu menu = new Menu();
        /*菜单选择父菜单，隐藏也需要显示 @zss  2017-09-07*/
       /* menu.setHide(false);*/
        return JSONUtils.ztree(findList(menu));
    }

    /**
     * 组织好用户所拥有访问权限的菜单
     *
     * @return 一个List结构的Menu对象
     */
    public List<Menu> formCurrentMenu() {
        Session session = UserUtils.getSession();
        User user = UserUtils.getCurrentUser();
        String userId = user.getId();
        List<Menu> userMenu = dao().findAccountMenu(userId);

        if (user.getAdmin()) {
            userMenu.addAll(findRoleMenu(Permissions.ROLE));
        }

        session.setAttribute(MENU_ATTRIBUTE, userMenu);
        return userMenu;
    }


    /**
     * 通过权限标识 role 查询其下所有菜单
     * @param role 角色的权限标识
     * @return 该角色下的所有菜单
     */
    public List<Menu> findRoleMenu(String role) {
        return dao().findRoleMenu(role);
    }

    /**
     * 查找所有可用的菜单信息，并封装为一个Map结构的对象返回
     *
     * @return 返回一个Map结构的 Menu 信息，key为Menu的id，value为Menu的name
     */
    public Map<String, String> map() {
        List<Menu> list = findList();
        Map<String, String> result = new HashMap<>();

        for (Menu menu : list) {
            result.put(menu.getId(), menu.getTreeName());
        }

        return result;
    }

    public List<Menu> findRoleMenuAndHide(String role) {
        return dao().findRoleMenuAndHide(role);
    }
    
    @Override
    @Transactional
    public void delete(String id) {
    	dao().delChilds(id);
    }
    
    /**
     * 
    * @Title: selectChilds 
    * @Description: 查询菜单下是否有子菜单
    * @param @param id
    * @param @return    设定文件 
    * @return List<Menu>    返回类型 
    * @throws
     */
    public List<Menu> selectChilds(String id){
    	 // 获取当前修改菜单的源数据
        Menu origin = get(id);
        // 查找当前菜单的所有子菜单
        String originParentIds = origin.makeSelfAsNewParentIds();
        return dao().findChildren(originParentIds);
    }
}
