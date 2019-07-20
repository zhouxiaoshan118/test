package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 二级菜单友链管理
 * Created by DW on 2017/12/12 16:42
 */
@Component
@RequestMapping(value = "/show/link")
@Menu(id = "sys:manage:linkmng", name = "友链管理", parent = SysMngMenu.class, order = 6, type = MenuEnum.PAGE)
public class LinkManager {

    @RequestMapping(value = "")
    public String showLinkMng(Model mode) {
        return "";
    }

    @Menu(id = "sys:manage:linkmng:test", name = "测试Menu", parent = LinkManager.class, order = 5, type = MenuEnum.PAGE)
    @RequestMapping(value = "/test")
    public String Test(Model mode) {
        return "";
    }

}
