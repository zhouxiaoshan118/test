package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理二级菜单
 * Created by DW on 2017/12/12 16:07
 */
@Controller
@Menu(id = "sys:manage:usermanager", name = "用户管理", parent = SysMngMenu.class, order = 3, type = MenuEnum.PAGE)
public class UserManage {


    @RequestMapping(value = "/userlist")
    @Menu(id = "sys:manage:usermanager:userlist", name = "用户列表", parent = UserManage.class, order = 1, type = MenuEnum.PAGE)
    public String showUserList(Model model) {
        return "";
    }

    @RequestMapping(value = "/rolelist")
    @Menu(id = "sys:manage:usermanager:rolelist", name = "角色列表", parent = UserManage.class, order = 2, type = MenuEnum.PAGE)
    public String showRoleList(Model model) {
        return "";
    }

    @RequestMapping(value = "/menumanager")
    @Menu(id = "sys:manage:usermanager:menumanager", name = "菜单管理", parent = UserManage.class, order = 3, type = MenuEnum.PAGE)
    public String menuManager(Model model) {
        return "";
    }
    @RequestMapping(value = "/officer")
    @Menu(id = "sys:manage:usermanager:officer", name = "部门人员", parent = UserManage.class, order = 4, type = MenuEnum.PAGE)
    public String showOfficer(Model model) {
        return "/modules/sys/officer";
    }
    @RequestMapping(value = "/previewUser")
    @Menu(id = "sys:manage:usermanager:previewUser", name = "演示用户", parent = UserManage.class, order = 5, type = MenuEnum.PAGE)
    public String showPreviewUser(Model model) {
        return "/modules/sys/priviewUser";
    }
}
