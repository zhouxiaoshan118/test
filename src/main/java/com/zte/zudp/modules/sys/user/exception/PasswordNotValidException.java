package com.zte.zudp.modules.sys.user.exception;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-16.
 */
public class PasswordNotValidException extends UserException {
    public PasswordNotValidException() {
        super("user.password.not.valid", null);
    }
}
