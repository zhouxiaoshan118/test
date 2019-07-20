package com.zte.zudp.modules.sys.user.utils;

import com.zte.zudp.common.config.Constants;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.exception.EmailNotValidException;
import com.zte.zudp.modules.sys.user.exception.LoginNameNotValidException;
import com.zte.zudp.modules.sys.user.exception.PasswordNotValidException;
import com.zte.zudp.modules.sys.user.exception.PhoneNotValidException;
import com.zte.zudp.modules.sys.user.exception.UserBlockedException;
import com.zte.zudp.modules.sys.user.exception.UserFreezedException;
import com.zte.zudp.modules.sys.utils.enums.AccountStatus;

/**
 * 校验类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-02.
 */
public class ValidateUtils {

    /**
     * 校验密码是否合法
     */
    public static void checkPassword(String password) {
        if (!isPassword(password)) {
            throw new PasswordNotValidException();
        }
    }

    /**
     * 校验密码是否合法
     */
    public static boolean isPassword(String password) {
        return password != null
                && password.length() <= User.PASSWORD_MAX_LENGTH
                && password.length() >= User.PASSWORD_MIN_LENGTH;
    }

    /**
     * 校验登录名是否合法
     */
    public static void checkLoginName(String loginName) {
        if (!isLoginName(loginName)) {
            throw new LoginNameNotValidException();
        }
    }

    /**
     * 校验登录名是否合法
     */
    public static boolean isLoginName(String loginName) {
        return loginName != null
                && loginName.length() <= User.USERNAME_MAX_LENGTH
                && loginName.length() >= User.USERNAME_MIN_LENGTH;
    }

    public static void checkPhone(String phone) {
        if (!isPhone(phone)) {
            throw new PhoneNotValidException();
        }
    }

    /**
     * 判断字符串是否为合法手机号码
     * @return 如果合法返回true，否则返回false
     */
    public static boolean isPhone(String phone) {
        return phone != null && phone.matches(Constants.MOBILE_PHONE_NUMBER_PATTERN);
    }

    public static void checkEmail(String email) {
        if (!isEmail(email)) {
            throw new EmailNotValidException();
        }
    }

    /**
     * 判断字符串是否为合法电子邮件
     * @return 如果合法返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return email != null && email.matches(Constants.EMAIL_PATTERN);
    }

    public static void checkStatus(AccountStatus status) {
        if (status == AccountStatus.BLOCKED) {
            throw new UserBlockedException();
        } else if (status == AccountStatus.FREEZED) {
            throw new UserFreezedException();
        }
    }
}
