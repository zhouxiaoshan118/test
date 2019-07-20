package com.zte.zudp.modules.sys.web;

import com.zte.zudp.common.ServletUtils;
import com.zte.zudp.common.config.Constants;
import com.zte.zudp.common.persistence.web.AbstractController;
import com.zte.zudp.common.utils.MessageUtils;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.system.config.shiro.filter.captcha.CaptchaValidateFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-11-14.
 */
@Controller
public class LoginController extends AbstractController<User> {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        //表示用户输入的验证码错误
        if (!StringUtils.isEmpty(request.getParameter(CaptchaValidateFilter.ERROR_NAME))) {
            model.addAttribute(Constants.ERROR,
                    MessageUtils.message(CaptchaValidateFilter.CAPTCHA_VALIDATE_ERROR));
        }

        Exception shiroLoginFailureEx =
                (Exception) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (shiroLoginFailureEx != null) {
            model.addAttribute(Constants.ERROR, shiroLoginFailureEx.getMessage());
            logger.warn(shiroLoginFailureEx.getMessage());
        }

        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            subject.logout();
        }

        model.addAttribute("isEnabledCaptcha", ServletUtils.isEnableCaptcha());

        return "/login";
    }

    /**
     * 登录失败后 或 未登录 会调用的方法
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            return redirectTo("/admin");
        }

        // 表示用户输入的验证码错误
        if (!StringUtils.isEmpty(request.getParameter(CaptchaValidateFilter.ERROR_NAME))) {
            model.addAttribute(Constants.ERROR,
                    MessageUtils.message(CaptchaValidateFilter.CAPTCHA_VALIDATE_ERROR));
        }

        Exception shiroLoginFailureEx =
                (Exception) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (shiroLoginFailureEx != null) {
            model.addAttribute(Constants.ERROR, shiroLoginFailureEx.getMessage());
            logger.warn(shiroLoginFailureEx.getMessage());
        }

        model.addAttribute("isEnabledCaptcha", ServletUtils.isEnableCaptcha());

        return "/login";
    }

    @RequestMapping("/admin")
    public String error() {
        return "/admin";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        logger.debug("unauthorized");

        return "/error/403";
    }

    @RequestMapping("/index")
    public String userIndex() {
        return "/home";
    }
    
    @RequestMapping({""})
    public String redirect() {
        return "forward:/winui/successproject";
    }


}
