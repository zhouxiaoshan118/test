package com.zte.zudp.system.config.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 账户令牌，保存账户信息
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public class AccountToken extends UsernamePasswordToken {

    /**
     * 验证码，如果要使用其他方式的验证码
     */
    private String captcha;

    /**
     * 是否是手机登录
     */
    private boolean phone;

    public AccountToken(String username, char[] password, boolean rememberMe, String host,
                        String captcha, boolean phone) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.phone = phone;
    }

    public String getCaptcha() {
        return captcha;
    }

    public boolean isPhone() {
        return phone;
    }
}
