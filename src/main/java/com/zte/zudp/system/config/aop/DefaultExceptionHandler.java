package com.zte.zudp.system.config.aop;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zte.zudp.common.config.Constants;

/**
 * 全局异常处理
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-27.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(Constants.ERROR_LOG);

    /**
     * 处理 shiro 的授权异常
     *
     * @return 权限不足的页面
     */
    @ResponseBody
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object processUnauthorizedException(Exception e, HandlerMethod handlerMethod) {
        return getObject(e, handlerMethod, HttpStatus.UNAUTHORIZED, Constants.AUTHORIZED_URL);
    }

    /**
     * 处理 shiro 的认证异常
     *
     * @return 登录页面
     */
    @ResponseBody
    @ExceptionHandler({UnauthenticatedException.class, AuthorizationException.class})
    @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
    public Object processUnauthenticatedException(Exception e, HandlerMethod handlerMethod) {
        logger.error(e.getMessage());

        Method returnType = handlerMethod.getMethod();
        Class<?> mav = returnType.getReturnType();
        if (mav.equals(ModelAndView.class) || mav.equals(String.class)) {
            return new ModelAndView(Constants.LOGIN_URL);
        } else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("error", e.getMessage());
            result.put("code", HttpStatus.PERMANENT_REDIRECT.value());
            return result;
        }
    }

    /**
     * 参数绑定异常
     */
    @ResponseBody
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object bindException(Exception e, HandlerMethod handlerMethod) {
        return getObject(e, handlerMethod, HttpStatus.BAD_REQUEST, Constants.BIND_URL);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object exception(Exception e, HandlerMethod handlerMethod) {
        return getObject(e, handlerMethod, HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_URL);
    }

    private Object getObject(Exception e, HandlerMethod handlerMethod,
                             HttpStatus code, String url) {
        logger.error(e.getMessage(), e);

        Method returnType = handlerMethod.getMethod();
        Class<?> mav = returnType.getReturnType();
        if (mav.equals(ModelAndView.class) || mav.equals(String.class)) {
            return new ModelAndView(url);
        } else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("error", e.getMessage());
            result.put("code", code.value());
            return result;
        }
    }
}
