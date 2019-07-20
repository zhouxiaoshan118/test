package com.zte.zudp.modules.sys.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zte.zudp.common.ServletUtils;
import com.zte.zudp.common.config.Constants;
import com.zte.zudp.common.utils.JSONUtils;
import com.zte.zudp.common.utils.StringUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-15.
 */
public class LogUtils {

    public static final Logger ERROR_LOG = LoggerFactory.getLogger(Constants.ERROR_LOG);
    public static final Logger ACCESS_LOG = LoggerFactory.getLogger(Constants.ACCESS_LOG);

    /**
     * 记录访问日志
     * [username][jsessionid][ip][accept][UserAgent][url][params][Referer]
     *
     * @param request
     */
    public static void logAccess(HttpServletRequest request) {
        String loginName = getLoginName();
        String jsessionId = request.getRequestedSessionId();
        String ip = ServletUtils.getIPAddr(request);
        String accept = request.getHeader("accept");
        String userAgent = request.getHeader("User-Agent");
        String url = request.getRequestURI();
        String params = getParams(request);
        String headers = getHeaders(request);

        StringBuilder info = new StringBuilder();
        info.append(StringUtils.block(loginName));
        info.append(StringUtils.block(jsessionId));
        info.append(StringUtils.block(ip));
        info.append(StringUtils.block(accept));
        info.append(StringUtils.block(userAgent));
        info.append(StringUtils.block(url));
        info.append(StringUtils.block(params));
        info.append(StringUtils.block(headers));
        info.append(StringUtils.block(request.getHeader("Referer")));
        getAccessLog().info(info.toString());
    }

    /**
     * 记录页面错误
     * 错误日志记录 [page/eception][username][statusCode][errorMessage][servletName][uri][exceptionName][ip][exception]
     *
     * @param request
     */
    public static void logPageError(HttpServletRequest request) {
        String username = getLoginName();

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");


        if (statusCode == null) {
            statusCode = 0;
        }

        StringBuilder s = new StringBuilder();
        s.append(StringUtils.block(t == null ? "page" : "exception"));
        s.append(StringUtils.block(username));
        s.append(StringUtils.block(statusCode));
        s.append(StringUtils.block(message));
        s.append(StringUtils.block(ServletUtils.getIPAddr(request)));

        s.append(StringUtils.block(uri));
        s.append(StringUtils.block(request.getHeader("Referer")));
        StringWriter sw = new StringWriter();

        while (t != null) {
            t.printStackTrace(new PrintWriter(sw));
            t = t.getCause();
        }

        s.append(StringUtils.block(sw.toString()));
        getErrorLog().error(s.toString());
    }

    protected static String getParams(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        return JSONUtils.parse(params);
    }

    private static String getHeaders(HttpServletRequest request) {
        Map<String, List<String>> headers = new HashMap<>();
        Enumeration<String> namesEnumeration = request.getHeaderNames();
        while (namesEnumeration.hasMoreElements()) {
            String name = namesEnumeration.nextElement();
            Enumeration<String> valueEnumeration = request.getHeaders(name);
            List<String> values = new ArrayList<>();
            while (valueEnumeration.hasMoreElements()) {
                values.add(valueEnumeration.nextElement());
            }
            headers.put(name, values);
        }
        return JSONUtils.parse(headers);
    }

    protected static String getLoginName() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

    public static Logger getAccessLog() {
        return ACCESS_LOG;
    }

    public static Logger getErrorLog() {
        return ERROR_LOG;
    }
}
