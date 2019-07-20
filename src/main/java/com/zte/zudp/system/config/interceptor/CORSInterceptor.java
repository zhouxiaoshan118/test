package com.zte.zudp.system.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * CORS 处理
 *
 * @author piumnl
 * @since on 2018-01-11.
 */
@Component
public class CORSInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {

        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", getAllowMethods(request));
        response.setHeader("Access-Control-Allow-Headers", getAllowHeaders(request));
        response.setHeader("Access-Control-Allow-Credentials","true");
        return true;
    }

    private String getAllowHeaders(HttpServletRequest request) {
        return "x-requested-with,Authorization," + request.getHeader("Access-Control-Request-Headers");
    }

    private String getAllowMethods(HttpServletRequest request) {
        return request.getMethod() + "," + request.getHeader("Access-Control-Request-Method");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
    }
}
