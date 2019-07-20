package com.zte.zudp.modules.sys.user.exception;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-16.
 */
public class PhoneNotValidException extends UserException {
    public PhoneNotValidException() {
        super("user.mobile.phone.number.not.valid", null);
    }
}
