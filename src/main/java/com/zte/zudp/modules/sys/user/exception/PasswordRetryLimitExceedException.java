package com.zte.zudp.modules.sys.user.exception;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public class PasswordRetryLimitExceedException extends UserException {
    public PasswordRetryLimitExceedException(int arg) {
        super("user.password.retry.limit.exceed", new Object[]{arg});
    }
}
