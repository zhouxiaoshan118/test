package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Component;

/**
 * 系统管理一级菜单
 * Created by DW on 2017/12/12 15:42
 */
@Component
@Menu(id = "sys:manage", name = "系统管理", parent = void.class, order = 1, type = MenuEnum.PAGE)
public class SysMngMenu {
}
