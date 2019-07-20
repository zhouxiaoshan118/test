package com.zte.zudp.system.config.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zte.zudp.common.config.Constants;
import com.zte.zudp.common.utils.DateUtils;

/**
 * 日志拦截器，用于记录一次请求所花费的时间
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-17.
 */
public class AccessLogInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(Constants.ACCESS_LOG);

    private static NamedThreadLocal<Long> namedThreadLocal =
            new NamedThreadLocal<>("record access waste time");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (logger.isDebugEnabled()) {
            long beginTime = System.currentTimeMillis();
            namedThreadLocal.set(beginTime);
            logger.debug("==> URI: {}, method: {}, beginTime: {}", request.getRequestURI(),
                    request.getMethod(), DateUtils.format(new Date(beginTime), DateUtils.DateFormat.TIME));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            logger.debug("== viewName: {}", modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        if (logger.isDebugEnabled()) {
            long endTime = System.currentTimeMillis();
            long beginTime = namedThreadLocal.get();
            float wasteTime = (endTime - beginTime) / 1000F;
            logger.debug("<== URI: {}, method: {}, endTime: {}, waste time: {}s",
                    request.getRequestURI(), request.getMethod(),
                    DateUtils.format(new Date(endTime), DateUtils.DateFormat.TIME), wasteTime);
        }
    }
}
