package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Component;

/**
 * 后台系统主界面
 * Created by DW on 2017/12/12 15:53
 */
@Component
@Menu(id = "sys:manage:index", name = "后台首页", parent = SysMngMenu.class, order = 1, type = MenuEnum.PAGE)
public class SysIndex {
}
