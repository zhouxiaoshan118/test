package com.zte.zudp.modules.sys.menu.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.menu.entity.Menu;
import com.zte.zudp.modules.sys.menu.service.MenuService;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
@RestController
@RequestMapping("${sys.url.admin}/sys/menu")
public class MenuController extends RESTfulController<Menu> {

    @Override
    protected void init() {
        super.init();
        setResourceIdentity(Permissions.MENU);
        setDefaultViewPath("admin/sys/menu");
    }

    public MenuService service() {
        return (MenuService) service;
    }

    @ResponseBody
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "/tree")
    public List<Map<String, Object>> tree() {
        return service().tree();
    }

    /**
     * 获取当前用户所有可用的菜单
     */
    @JsonView(View.Default.class)
    @RequiresUser
    @RequestMapping(value = "/pull")
    public ResponseEntity<List<Menu>> pull() {
        List<Menu> form = service().formCurrentMenu();

        for (Menu menu : form) {
            String parentIds = StringUtils.get(menu.getParentIds());
            if (parentIds.startsWith(",")) {
                menu.setParentIds(parentIds.substring(1));
            }
        }

        return new ResponseEntity<>(form, HttpStatus.OK);
    }
    
    @HasPermission(Permission.DELETE)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<Menu> delete(@PathVariable("id") String t) {
        service().delete(t);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * 
    * @Title: selectChilds 
    * @Description: 查询菜单下是否子菜单，返回List，以备后面前面页面可以显示还有哪些子菜单 
    * @param @param t
    * @param @return    设定文件 
    * @return ResponseEntity<Menu>    返回类型 
    * @throws
     */
    @HasPermission(Permission.VIEW)
    @RequestMapping(value = "/selectChilds/{id}", method = RequestMethod.GET)
    public  ResponseEntity<List<Menu>> selectChilds(@PathVariable("id") String t){
    	 List<Menu> mList =  service().selectChilds(t);
         return new ResponseEntity<>(mList, HttpStatus.OK);
    }
}
