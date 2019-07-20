package com.zte.zudp.modules.sys.user.exception;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-16.
 */
public class EmailNotValidException extends UserException {
    public EmailNotValidException() {
        super("user.email.not.valid", null);
    }
}
