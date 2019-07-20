package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 历史成功项目内容管理
 * Created by DW on 2017/12/16 16:17
 */
@Controller
@RequestMapping(value = "/show/page/spmng")
@Menu(id = "sys:content:SPMng", name = "成功项目管理", parent = ContentManagerMenu.class, order = 1, type = MenuEnum.PAGE)
public class SuccessProjectContentMng {

    @RequestMapping(value = {})
    public String showSpMng() {
        return "/modules/sys/successProject/successProject";
    }
}
