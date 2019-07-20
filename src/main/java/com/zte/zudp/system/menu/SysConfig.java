package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统设置二级菜单
 * Created by DW on 2017/12/12 16:27
 */
@Controller
@RequestMapping(value = "/show/page")
@Menu(id = "sys:manage:config", name = "系统设置", parent = SysMngMenu.class, order = 5, type = MenuEnum.PAGE)
public class SysConfig {

    @RequestMapping(value = "/baseinfo")
    @Menu(id = "sys:manage:config:baseinfo", name = "系统基本参数", parent = SysConfig.class, order = 1 , type = MenuEnum.PAGE)
    public String showBaseSysInfo(Model model) {
        return "";
    }

    @RequestMapping(value = "/syslog")
    @Menu(id = "sys:manage:config:syslogmng", name = "系统日志管理", parent = SysConfig.class, order = 2 , type = MenuEnum.PAGE)
    public String syslogMng(Model model) {
        return "";
    }

    @RequestMapping(value = "/dict")
    @Menu(id = "sys:manage:config:dict", name = "用户字典管理", parent = SysConfig.class, order = 3 , type = MenuEnum.PAGE)
    public String showUserDict(Model model) {
//        System.out.println("xxx");
        return "/modules/sys/Mydict";
    }
}
