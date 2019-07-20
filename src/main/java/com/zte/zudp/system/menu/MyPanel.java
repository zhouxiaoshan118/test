package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 我的面板
 * Created by DW on 2017/12/12 15:56
 */
@Component
@Menu(id = "sys:manage:mypanel", name = "我的面板", parent = SysMngMenu.class, order = 2, type = MenuEnum.PAGE)
public class MyPanel {

    @RequestMapping(value = "")
    @Menu(id = "sys:manage:mypanel:persioninfo", name = "个人信息", parent = MyPanel.class, order = 1, type = MenuEnum.PAGE)
    public String showPersionInfo(Model model) {
        return "";
    }

    @RequestMapping(value = "")
    @Menu(id = "sys:manage:mypanel:changepassword", name = "修改密码", parent = MyPanel.class, order = 2, type = MenuEnum.PAGE)
    public String showChangePassword() {
        return "";
    }

    @RequestMapping(value = "")
    @Menu(id = "sys:manage:mypanel:loginfo", name = "日志信息", parent = MyPanel.class, order = 3, type = MenuEnum.PAGE)
    public String ShowLogInfo(Model model) {
        return "";
    }

}
