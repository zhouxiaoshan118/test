package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Component;

/**
 * 拓展模块一级菜单
 * Created by DW on 2017/12/12 15:51
 */
@Component
@Menu(id = "sys:extend", name = "拓展模块", parent = void.class, order = 3, type = MenuEnum.PAGE)
public class ExtendsMenu {
}
