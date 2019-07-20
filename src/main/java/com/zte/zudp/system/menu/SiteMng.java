package com.zte.zudp.system.menu;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.enums.MenuEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 二级菜单网站管理
 * Created by DW on 2017/12/12 16:50
 */
@Controller
@Menu(id = "sys:content:sitemng", name = "网站管理", parent = ContentManagerMenu.class, order = 1, type = MenuEnum.PAGE)
public class SiteMng {

    @RequestMapping(value = "/cloumnmng")
    @Menu(id = "sys:content:sitemng:cloumnmng", name = "网站栏目管理", parent = SiteMng.class, order = 1, type = MenuEnum.PAGE)
    public String columnMng(Model model) {
        return "";
    }

    @RequestMapping(value = "/articlelist")
    @Menu(id = "sys:content:sitemng:articlelist", name = "所有文章列表", parent = SiteMng.class, order = 2, type = MenuEnum.PAGE)
    public String articleList(Model model) {
        return "";
    }

    @RequestMapping(value = "/viewingarticle")
    @Menu(id = "sys:content:sitemng:viewingarticle", name = "待审核文章", parent = SiteMng.class, order = 3, type = MenuEnum.PAGE)
    public String viewingArticle(Model model) {
        return "";
    }
    
    @RequestMapping(value = "/dailyArticle")
    @Menu(id = "sys:content:sitemng:dailyArticle", name = "日常报告", parent = SiteMng.class, order = 4, type = MenuEnum.PAGE)
    public String dailyArticle(Model model) {
        return "/modules/sys/dailyArticleChecked";
    }

    @RequestMapping(value = "/wcdailyArticle")
    @Menu(id = "sys:content:sitemng:wcdailyArticle", name = "待审核日常报告", parent = SiteMng.class, order = 5, type = MenuEnum.PAGE)
    public String wcdailyArticle(Model model) {
        return "/modules/sys/dailyArticle";
    }
}
