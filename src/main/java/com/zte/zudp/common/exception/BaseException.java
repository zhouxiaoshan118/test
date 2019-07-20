package com.zte.zudp.common.exception;

import java.util.Arrays;

import org.springframework.util.StringUtils;

import com.zte.zudp.common.utils.MessageUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public class BaseException extends RuntimeException {

    /* 所属模块 */
    private String module;

    /* 错误码 */
    private String code;

    /* 错误码对应的参数 */
    private Object[] args;

    /* 错误消息 */
    private String errorMessage;

    public BaseException(String module, String code, Object[] args, String errorMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.errorMessage = errorMessage;
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String errorMessage) {
        this(module, null, null, errorMessage);
    }

    public BaseException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        String message = errorMessage;

        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }

        return message;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "module='" + module + '\'' +
                ", code='" + code + '\'' +
                ", args=" + Arrays.toString(args) +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
