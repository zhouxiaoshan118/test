package com.zte.zudp.modules.sys.user.web;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zte.zudp.common.config.Constants;
import com.zte.zudp.common.persistence.web.TraditionController;
import com.zte.zudp.common.utils.MessageUtils;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.service.PasswordService;
import com.zte.zudp.modules.sys.user.utils.UserUtils;

/**
 * 用户管理自己的个人信息
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-16.
 */
@Controller
@RequestMapping("${sys.url.admin}/sys/profile")
public class ProfileController extends TraditionController<User> {

    @Autowired
    private PasswordService passwordService;

    @RequiresUser()
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String infoGet(Model model) {
        model.addAttribute("user", UserUtils.getCurrentUser());
        return "sys/profile/info";
    }

    @RequiresUser()
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String info(User user, Model model) {
        service.update(user);
        return "redirect:/admin/sys/profile";
    }

    @RequiresUser()
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePasswordGet() {
        return "sys/profile/password";
    }

    @RequiresUser()
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(User user, String newPassword, String oldPassword, Model model) {
        if (StringUtils.isEmpty(newPassword)) {
            model.addAttribute(Constants.ERROR,
                    MessageUtils.message("parameter.not.null", "newPassword"));
        }
        if (StringUtils.isEmpty(oldPassword)) {
            model.addAttribute(Constants.ERROR,
                    MessageUtils.message("parameter.not.null", "oldPassword"));
        }

        boolean result = passwordService.changePassword(user, newPassword, oldPassword);
        if (result) {
            return "redirect:/admin/sys/profile/info";
        } else {
            return "redirect:/admin/sys/profile/changePassword?id=" + user.getId();
        }
    }
}
