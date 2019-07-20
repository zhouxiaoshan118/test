package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Component;

/**
 * 内容管理一级菜单
 * Created by DW on 2017/12/12 15:49
 */
@Component
@Menu(id = "sys:content", name = "内容管理", parent = void.class, order = 2, type = MenuEnum.PAGE)
public class ContentManagerMenu {
}
