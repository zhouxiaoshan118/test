package com.zte.zudp.modules.sys.user.exception;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public class UserBlockedException extends UserException {

    public UserBlockedException() {
        super("user.blocked", null);
    }
}
