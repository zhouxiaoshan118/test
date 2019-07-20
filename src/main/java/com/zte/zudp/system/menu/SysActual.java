package com.zte.zudp.system.menu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;

/**
 * 二级菜单拓展模块
 * Created by DW on 2017/12/12 16:50
 */
@Controller
@RequestMapping(value = "/show/page")
@Menu(id = "sys:extend:Actual", name = "实战演练", parent = ExtendsMenu.class, order = 3, type = MenuEnum.PAGE)
public class SysActual {
	 @RequestMapping(value = "/actual")
	    @Menu(id = "sys:extend:Actual:dict", name = "实战项目", parent = SysActual.class, order = 1 , type = MenuEnum.PAGE)
	    public String showUserDict(Model model) {
//	        System.out.println("xxx");
	        return "/modules/sys/Actuals";
	    }
}
