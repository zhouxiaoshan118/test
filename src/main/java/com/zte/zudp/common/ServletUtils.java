package com.zte.zudp.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.zte.zudp.common.config.Configuration;
import com.zte.zudp.common.config.Constants;
import com.zte.zudp.common.utils.SpringUtils;
import com.zte.zudp.system.cache.CacheType;
import com.zte.zudp.system.cache.CacheUtils;
import com.zte.zudp.system.config.shiro.filter.captcha.CaptchaValidateFilter;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-15.
 */
public class ServletUtils {

    /**
     * ctx
     * @return
     */
    public static String contentPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    }

    /**
     * @param request
     * @return
     */
    public static String getIPAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getIPAddr() {
        return getIPAddr(getHttpRequest());
    }

    public static String getRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }

    public static String getRemoteHost() {
        return getRemoteHost(getHttpRequest());
    }

    /**
     * 获取当前请求的 HttpServletRequest 对象<br>
     * 注意：<br>
     *      POST 表单 enctype=multipart/form-data 提交时，此方法返回的对象不会正常工作
     */
    public static HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前请求的 HttpServletResponse 对象
     */
    public static HttpServletResponse getResponse() {
        return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 是否是 ajax 异步请求
     *
     * @return 判断请求是否为异步请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");

        boolean isReturnJson = (accept != null && accept.contains("application/json"));
        boolean isAjaxRequest = xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest");

        return (isReturnJson || isAjaxRequest);
    }

    /**
     * 动态为用户控制验证码的显示与隐藏
     *
     * @return 如果启用了则返回true，否则返回false
     */
    public static boolean isEnableCaptcha() {
        CaptchaValidateFilter captchaFilter = SpringUtils.getBean(CaptchaValidateFilter.class);
        Object o = CacheUtils.get(CacheType.LOGIN_RECORD.getName(), ServletUtils.getIPAddr());
        boolean result = o != null && o instanceof Boolean && (Boolean) o;
        // return captchaFilter.isCaptchaEbabled() && result;
        return true;
    }

    /**
     * 判断当前访问的URI是否在访问静态文件
     *
     * @return 如果URI是访问/static下的文件则返回 true ，否则返回false。
     */
    public static boolean isStaticFileRequest(HttpServletRequest request) {
        String systemRootPath = Configuration.getProperty(Constants.SYSTEM_ROOT_PATH);
        String uri = request.getRequestURI();

        if (systemRootPath == null || "/".equals(systemRootPath)) {
            systemRootPath = "";
        }

        int length = systemRootPath.length();
        if (length > uri.length()) {
            return false;
        }
        String subURI = uri.substring(length);
        return subURI.contains("/static/") || subURI.endsWith(Constants.CAPTCHA_URL);
    }
}
