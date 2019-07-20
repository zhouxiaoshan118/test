package com.zte.zudp.modules.sys.user.exception;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public class LoginNameNotValidException extends UserException {
    public LoginNameNotValidException() {
        super("user.loginName.not.match", null);
    }
}
