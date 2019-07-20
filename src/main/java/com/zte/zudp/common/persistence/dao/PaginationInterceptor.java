package com.zte.zudp.common.persistence.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zte.zudp.common.persistence.entity.page.Page;
import com.zte.zudp.common.utils.SQLUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-06.
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class})
})
public class PaginationInterceptor implements Interceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {
        if (Page.get() == null) {
            return invocation.proceed();
        }

        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
            // 可以分离出最原始的的目标类)
            while (metaStatementHandler.hasGetter("h")) {
                Object object = metaStatementHandler.getValue("h");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            // 分离最后一个代理对象的目标类
            while (metaStatementHandler.hasGetter("target")) {
                Object object = metaStatementHandler.getValue("target");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }

            Page page = Page.get();
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

            if (boundSql.getSql().toLowerCase().startsWith("select")) {
                MappedStatement mappedStatement =
                        (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
                Long total = getTotalRecord(boundSql, mappedStatement, (Connection) invocation.getArgs()[0]);
                page.initialized(total);
                logger.trace("the query for '{}' has {} total number.", boundSql.getSql(), total);

                metaStatementHandler.setValue("delegate.boundSql.sql", SQLUtils.getPageSQL(boundSql, page));
            }

            return invocation.proceed();
        }

        return null;
    }

    private Long getTotalRecord(BoundSql boundSql, MappedStatement mappedStatement,
                                Connection connection) {
        String countSql = SQLUtils.getCountSQL(boundSql.getSql());

        BoundSql countBoundSql = newBoundSQL(mappedStatement, boundSql, countSql);
        Object parameterObject = boundSql.getParameterObject();
        ParameterHandler parameterHandler =
                new DefaultParameterHandler(mappedStatement, parameterObject, countBoundSql);
        return SQLUtils.queryTotalRecord(connection, countSql, parameterHandler);
    }

    private BoundSql newBoundSQL(MappedStatement mappedStatement, BoundSql boundSql, String sql) {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();

        return new BoundSql(mappedStatement.getConfiguration(),
                sql, parameterMappings, parameterObject);
    }

    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    public void setProperties(Properties prop) {
    }
}