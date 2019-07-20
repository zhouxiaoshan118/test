package com.zte.zudp.modules.sys.role.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.persistence.entity.AssociationEntity;
import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.common.utils.DaoUtils;
import com.zte.zudp.common.utils.IDUtils;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.menu.entity.Menu;
import com.zte.zudp.modules.sys.menu.service.MenuService;
import com.zte.zudp.modules.sys.role.dao.RoleDao;
import com.zte.zudp.modules.sys.role.entity.Role;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.service.UserService;
import com.zte.zudp.modules.sys.user.utils.UserUtils;

/**
 * 角色Service
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends BusinessService<Role> {

    public static final String ASSIGNED_USERS = "assignedUsers";
    public static final String UNASSIGNED_USERS = "unassignedUsers";

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    private RoleDao dao() {
        return (RoleDao) dao;
    }

    @Transactional()
    public void save(Role role, String[] menuIds) {
        if (role.isNewRecord()) {
            role.setId(IDUtils.defaultUUID());
            insert(role);

            if (menuIds != null) {
                dao().insertSrmBash(DaoUtils.generatedList(role.getId(), menuIds));
            }
        } else {
            update(role);
            dao().deleteSrmBash(role);

            if (menuIds != null) {
                dao().insertSrmBash(DaoUtils.generatedList(role.getId(), menuIds));
            }
        }
    }

    /**
     * 查找所有已激活的角色信息
     *
     * @param roleIds .
     * @return .
     */
    public Set<Role> findActivedRoles(Set<String> roleIds) {
        return dao().findActivedRoles(new ArrayList<>(roleIds));
    }

    /**
     * 查找指定角色的所有可用的权限信息
     *
     * @param role 指定的角色信息
     * @return 该角色拥有的可用的权限信息
     */
    public Set<String> findActivedPermissionIds(Role role) {
        return dao().findActivedPermissionIds(role);
    }

    /**
     * 查找指定角色的所有可用的权限信息
     *
     * @param role 指定的角色信息
     * @return 该角色拥有的可用的权限信息
     */
    public Set<Menu> findActivedPermission(Role role) {
        Set<Menu> result = new HashSet<>();
        Set<String> permissionIds = dao().findActivedPermissionIds(role);
        if (permissionIds != null) {
            for (String permissionId : permissionIds) {
                result.add(new Menu(permissionId));
            }
        }
        return result;
    }

    /**
     * 获取关联了roleId和未关联roleId的用户信息集合
     *
     * @param role 指定的角色
     * @return 关联了roleId和未关联roleId的用户信息集合<br>
     * Map.key: {@link #ASSIGNED_USERS} 关联了 role 的集合 key <br>
     * Map.key: {@link #UNASSIGNED_USERS} 未关联了 role 的集合 key
     */
    public HashMap<String, List> findUsersSra(Role role) {
        List<User> users = userService.findList();
        List<User> assignUsers = dao().findAccountsSra(role);

        boolean bool;
        List<User> assignUserList = new ArrayList<>();
        List<User> unassignUserList = new ArrayList<>();
        for (User user : users) {
            bool = false;
            for (User assignUser : assignUsers) {
                if (assignUser.getId().equals(user.getId())) {
                    bool = true;
                    assignUserList.add(user);
                }
            }

            if (!bool) {
                unassignUserList.add(user);
            }
        }

        HashMap<String, List> result = new HashMap<>();
        result.put(ASSIGNED_USERS, assignUserList);
        result.put(UNASSIGNED_USERS, unassignUserList);
        return result;
    }

    /**
     * 角色分配操作
     *
     * @param role       要分配的角色
     * @param accountIds 要关联的账户id集合
     */
    @Transactional()
    public void saveSra(Role role, String[] accountIds) {
        dao().deleteSraBash(role);
        dao().insertSraBash(DaoUtils.generatedList(role.getId(), accountIds));
    }

    /**
     * 移除指定账户和角色的关联关系
     *
     * @param id        角色id
     * @param accountId 账户id
     */
    @Transactional()
    public void deleteSra(String id, String accountId) {
        dao().deleteSra(new AssociationEntity(id, accountId));
    }

    /**
     * 获取当前用户及当前角色所拥有的所有菜单，并在这些菜单中选择出ztree格式数据，找出那些是已经选择的
     * @param roleId
     * @return
     */
    public List<Map<String, Object>> tree(String roleId) {
        Role role = get(roleId);

        User currentUser = UserUtils.getCurrentUser();
        List<Menu> userMenus =
                currentUser.getAdmin()? menuService.findList() : menuService.formCurrentMenu();

        List<Menu> roleMenus = new ArrayList<>();
        if (role != null) {
            roleMenus = menuService.findRoleMenuAndHide(role.getRole());
        }

        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map;
        for (Menu userMenu : userMenus) {
            map = new HashMap<>();
            map.put("id", userMenu.getId());
            map.put("pId", userMenu.getParentId());
            map.put("pIds", userMenu.getParentIds());
            map.put("name", userMenu.getTreeName());
            map.put("checked", false);
            for (Menu roleMenu : roleMenus) {
                if (roleMenu.getId().equals(userMenu.getId())) {
                    map.put("checked", true);
                }
            }

            result.add(map);
        }

        return result;
    }

    /**
     * 保存角色和菜单的关联关系
     * @param roleId 角色id
     * @param menuIds 关联的菜单id
     */
    @Transactional()
    public void saveSrm(String roleId, String[] menuIds) {
        Role role = new Role(roleId);
        dao().deleteSrmBash(role);

        dao().insertSrmBash(DaoUtils.generatedList(role.getId(), menuIds));
    }


    /**
     * 查找当前用户下可以看到的所有用户，当前尚未实现 根据当前用户所在组织获取对应可查看的用户 功能
     */
    public List<Map<String, Object>> associatedUsers(String roleId) {
        List<User> list = userService.findList();
        List<User> associatedUsers = dao().findAccountsSra(new Role(roleId));

        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> temp;
        for (User user : list) {
            temp = new HashMap<>();
            temp.put("id", user.getId());
//            temp.put("name", user.getPartymember() != null ? user.getPartymember().getName() : "");
            temp.put("name", user.getLoginName());
            temp.put("checked", false);

            for (User associatedUser : associatedUsers) {
                if (associatedUser.getId().equals(user.getId())) {
                    temp.put("checked", true);
                }
            }
            result.add(temp);
        }

        return result;
    }

    /**
     * 通过 role 直接查询是否存在该标识
     * @param role 角色标识
     * @param id 权限标识
     * @return 不存在返回 true ，否则返回false
     */
    public boolean getByRole(String role, String id) {
        Role r = new Role();
        r.setRole(role);
        List<Role> list = dao().findList(r);
        return list == null || list.size() == 0 || StringUtils.equals(list.get(0).getId(), id);
    }
}
