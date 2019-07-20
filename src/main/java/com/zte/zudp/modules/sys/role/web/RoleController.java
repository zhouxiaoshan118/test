package com.zte.zudp.modules.sys.role.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.common.utils.CollectionUtils;
import com.zte.zudp.modules.sys.role.entity.Role;
import com.zte.zudp.modules.sys.role.service.RoleService;

/**
 * 角色Contoller
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
@RestController
@RequestMapping("${sys.url.admin}/sys/role")
public class RoleController extends RESTfulController<Role> {

    @Override
    protected void init() {
        super.init();
        setResourceIdentity(Permissions.ROLE);
        setDefaultViewPath("admin/sys/role");
    }

    public RoleService service() {
        return (RoleService) service;
    }

    /**
     * 获取指定角色所拥有的所有菜单
     */
    @HasPermission(Permission.ASSIGN)
    @RequestMapping(value = "/{id}/menu", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Map<String, Object>> tree(@PathVariable("id") String roleId) {
        return service().tree(roleId);
    }

    /**
     * 分配菜单到指定角色
     */
    @HasPermission(Permission.ASSIGN)
    @RequestMapping(value = "/{id}/menu/assign", method = RequestMethod.POST)
    public ResponseEntity<Role> assignMenu(@PathVariable("id") String roleId, @RequestBody String[] menuIds) {
        service().saveSrm(roleId, menuIds);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 获取当前用户所能知道的所有用户信息
     * @return 当前用户所知道的用户信息
     */
    @HasPermission(Permission.ASSOCIATED)
    @RequestMapping("/{id}/user")
    public List<Map<String, Object>> associatedUsers(@PathVariable("id") String roleId) {
        return service().associatedUsers(roleId);
    }

    /**
     * 角色关联用户
     */
    @HasPermission(Permission.ASSOCIATED)
    @RequestMapping(value = "/{id}/user/associated", method = RequestMethod.POST)
    public ResponseEntity<Role> assignUser(@PathVariable("id") String roleId, @RequestBody String[] userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        service().saveSra(new Role(roleId), userIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 校验角色标识是否存在
     *
     * @return 如果存在返回false，否则返回true
     */
    @RequestMapping(value = "/check/", method = RequestMethod.POST)
    @HasPermission(Permission.VIEW)
    public boolean check(String role, String id) {
        return service().getByRole(role, id);
    }
}
