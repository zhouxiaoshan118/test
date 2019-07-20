package com.zte.zudp.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zte.zudp.common.utils.StringUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-11-15.
 */
public class CaptchaUtils {

    public static final String CAPTCHA_KEY = "simpleCaptcha";

    public static final String CODE_TIME_KEY = "codeTime";

    /**
     *  校验验证码是否输入正确
     *
     * @param request 请求
     * @param userCode 用户输入的验证码
     * @return {captcha.no_effective_time} 验证码已失效
     *         {captcha.validate.error} 验证码错误
     *         null 验证码通过
     */
    public static String validateCaptcha(HttpServletRequest request, String userCode) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "captcha.no_effective_time";
        }

        Long codeTime = Long.valueOf(
                request.getSession(false).getAttribute(CODE_TIME_KEY).toString());
        Object oriCode = session.getAttribute(CAPTCHA_KEY);

        if (oriCode == null || (System.currentTimeMillis() - codeTime) / 60000 > 5) {
            return "captcha.no_effective_time";
        }

        if(StringUtils.isEmpty(userCode) ||  !(userCode.equalsIgnoreCase(oriCode.toString()))) {
                return "captcha.validate.error";
        }else {
            session.removeAttribute(CAPTCHA_KEY);
            session.removeAttribute(CODE_TIME_KEY);
            return null;
        }

    }
}
