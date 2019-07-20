package com.zte.zudp.modules.sys.utils.validate;

import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.exception.LoginNameNotValidException;
import com.zte.zudp.modules.sys.user.exception.PasswordNotValidException;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-01.
 */
public class UserValidator {
    public void check(String loginName, String password) {
        if (!checkLoginName(loginName)) {
            throw new LoginNameNotValidException();
        }

        if (!checkPassword(password)) {
            throw new PasswordNotValidException();
        }
    }

    /**
     * 校验密码是否合法
     */
    public boolean checkPassword(String password) {
        return checkPassword(password.toCharArray());
    }

    /**
     * 校验密码是否合法
     */
    public boolean checkPassword(char[] password) {
        return password.length <= User.PASSWORD_MAX_LENGTH &&
                password.length >= User.PASSWORD_MIN_LENGTH;
    }

    /**
     * 校验登录名是否合法
     */
    public boolean checkLoginName(String loginName) {
        return loginName.length() <= User.USERNAME_MAX_LENGTH &&
                loginName.length() >= User.USERNAME_MIN_LENGTH;
    }
}
