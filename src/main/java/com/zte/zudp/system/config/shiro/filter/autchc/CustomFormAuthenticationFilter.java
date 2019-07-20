package com.zte.zudp.system.config.shiro.filter.autchc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.system.config.shiro.token.AccountToken;

/**
 * 用户登录的拦截器
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-13.
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /* 默认成功跳转的URL */
    private String defaultSuccessURL;

    /* 默认管理员成功跳转的URL */
    private String adminDefaultSuccessURL;

    // private final UserService userService;

    // @Autowired
    // public CustomFormAuthenticationFilter(UserService userService) {
    //     this.userService = userService;
    // }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String loginName = StringUtils.get(StringUtils.trim(request.getParameter("loginName")));
        String password = StringUtils.get(getPassword(request));
        String captcha = StringUtils.trim(request.getParameter("captcha"));

        boolean phone = isPhone(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new AccountToken(loginName, password.toCharArray(), rememberMe, host, captcha, phone);
    }

    public boolean isPhone(ServletRequest request) {
        String phone = StringUtils.trim(request.getParameter("phone"));

        boolean result = false;
        if (phone != null) {
            result = Boolean.getBoolean(phone);
        }

        return result;
    }

    @Override
    public String getSuccessUrl() {
        // String loginName = (String) SecurityUtils.getSubject().getPrincipal();
        // User user = userService.getUserByLoginName(loginName);

        // if (user != null && user.getAdmin()) {
        //     return getAdminDefaultSuccessURL();
        // }
        // return getDefaultSuccessURL();
        return "/index";
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }

    // ---------------------------------------------------------------------------------------------

    public String getDefaultSuccessURL() {
        return defaultSuccessURL;
    }

    public void setDefaultSuccessURL(String defaultSuccessURL) {
        this.defaultSuccessURL = defaultSuccessURL;
    }

    public String getAdminDefaultSuccessURL() {
        return adminDefaultSuccessURL;
    }

    public void setAdminDefaultSuccessURL(String adminDefaultSuccessURL) {
        this.adminDefaultSuccessURL = adminDefaultSuccessURL;
    }
}
