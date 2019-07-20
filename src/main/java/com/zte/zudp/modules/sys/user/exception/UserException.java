package com.zte.zudp.modules.sys.user.exception;

import com.zte.zudp.common.exception.BaseException;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public class UserException extends BaseException {
    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
