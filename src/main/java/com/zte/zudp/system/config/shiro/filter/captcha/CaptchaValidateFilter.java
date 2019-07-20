package com.zte.zudp.system.config.shiro.filter.captcha;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;

import com.zte.zudp.common.config.Constants;
import com.zte.zudp.system.CaptchaUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-11-15.
 */
public class CaptchaValidateFilter extends AccessControlFilter {

    public static final String CAPTCHA_VALIDATE_ERROR = Constants.CAPTCHA_VALIDATE_ERROR;

    public static final String ERROR_NAME = "captchaError";

    public static final String CAPTCHA_PARAM = "jcaptchaCode";

    @Value("${zudp.captcha.enabled}")
    private boolean captchaEbabled;

    private String captchaParam = CAPTCHA_PARAM;

    private String capatchaErrorUrl;

    /**
     * 是否开启captcha
     */
    public void setCaptchaEbabled(boolean captchaEbabled) {
        this.captchaEbabled = captchaEbabled;
    }

    public boolean isCaptchaEbabled() {
        return captchaEbabled;
    }

    /**
     * 前台传入的验证码
     */
    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    public void setCapatchaErrorUrl(String capatchaErrorUrl) {
        this.capatchaErrorUrl = capatchaErrorUrl;
    }

    public String getCapatchaErrorUrl() {
        return capatchaErrorUrl;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        request.setAttribute("captchaEbabled", captchaEbabled);
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 验证码禁用 或不是表单提交 允许访问
        // 验证码被禁用 captchaEbabled = false
        // 登录时提交验证码时肯定为 post 提交
        if (!captchaEbabled || "get".equals(httpRequest.getMethod().toLowerCase())) {
            return true;
        }

        // 验证码被启用 captchaEbabled = true，并且登录超过指定次数
        // Object o = CacheUtils.get(CacheType.LOGIN_RECORD.getName(), ServletUtils.getIPAddr());
        // 缓存中存在值并且为true
        // if (o != null && o instanceof Boolean && (Boolean)o) {
            String res = CaptchaUtils.validateCaptcha(httpRequest, httpRequest.getParameter(captchaParam));
            if (res != null) {
                request.setAttribute(ERROR_NAME, res);
            }
            return res == null;
        // } else {
        //     return true;
        // }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        redirectToLogin(request, response);
        return false;
    }

    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, getCapatchaErrorUrl());
    }

}
