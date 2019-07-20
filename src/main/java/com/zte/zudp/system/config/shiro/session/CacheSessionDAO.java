package com.zte.zudp.system.config.shiro.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zte.zudp.common.config.Constants;

/**
 * shiro的Session管理类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-23.
 */
public class CacheSessionDAO extends EnterpriseCacheSessionDAO {

    private Logger logger = LoggerFactory.getLogger(Constants.SYSTEM_SESSION_LOG);

    @Override
    protected Serializable doCreate(Session session) {
        // HttpServletRequest request = ServletUtils.getHttpRequest();
        Serializable serializable = super.doCreate(session);
        // logger.info("do create session for uri {}, the session is {}",
        //         request != null ? request.getRequestURI() : "", session);
        return serializable;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.info("do read session with id [{}]", sessionId);
        return super.doReadSession(sessionId);
    }

    @Override
    protected void doUpdate(Session session) {
        // HttpServletRequest request = ServletUtils.getHttpRequest();
        // if (isNullSession(session) || isStaticFileRequest(request)) {
        //     return;
        // }

        // 如果是视图文件，则不更新SESSION
        // String requestURI = request.getRequestURI();
        // if (requestURI != null
        //         && requestURI.startsWith(Configuration.getProperty(Constants.WEB_VIEW_PREFIX))
        //         && requestURI.endsWith(Configuration.getProperty(Constants.WEB_VIEW_SUFFIX))) {
        //     return;
        // }

        super.doUpdate(session);
        logger.info("do update session {}", session);
    }

    @Override
    protected void doDelete(Session session) {
        if (isNullSession(session)) {
            return;
        }

        super.doDelete(session);
        logger.info("do delete session {}", session);
    }

    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        Session session;
        // HttpServletRequest request = ServletUtils.getHttpRequest();

        // if (isStaticFileRequest(request)) {
        //     return null;
        // }

        // if (request != null) {
        //     session = (Session) request.getAttribute("session_" + sessionId);
        //     if (session != null) {
        //         return session;
        //     }
        // }

        session = super.readSession(sessionId);
        // if (request != null) {
        //     request.setAttribute("session_" + sessionId, session);
        // }
        // logger.info("read session {}, uri {}", sessionId,
        //         request != null ? request.getRequestURI() : "");

        return super.readSession(sessionId);
    }

    private boolean isStaticFileRequest(HttpServletRequest request) {
        // return request != null && ServletUtils.isStaticFileRequest(request);
        return true;
    }

    private boolean isNullSession(Session session) {
        // return session == null || session.getId() == null;
        return true;
    }
}
