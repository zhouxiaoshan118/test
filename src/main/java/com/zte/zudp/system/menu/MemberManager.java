package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *会员管理二级菜单
 * Created by DW on 2017/12/12 16:17
 */
@Component
@Menu(id = "sys:manage:membermanager", name = "会员管理", parent = SysMngMenu.class, order = 4, type = MenuEnum.PAGE)
public class MemberManager {

    @RequestMapping(value = "")
    @Menu(id = "sys:manage:usermanager:memberlist", name = "会员注册列表", parent = MemberManager.class, order = 1, type = MenuEnum.PAGE)
    public String showMemberList() {
        return "";
    }

    @RequestMapping(value = "")
    @Menu(id = "sys:manage:usermanager:membermsg", name = "会员留言管理", parent = MemberManager.class, order = 2, type = MenuEnum.PAGE)
    public String showMemberMsg() {
        return "";
    }

    @RequestMapping(value = "")
    @Menu(id = "sys:manage:usermanager:memberlevelmng", name = "会员等级管理", parent = MemberManager.class, order = 3, type = MenuEnum.PAGE)
    public String showMemberLevelMng() {
        return "";
    }
}
