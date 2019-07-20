package com.zte.zudp.common.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;

import com.zte.zudp.common.config.Configuration;
import com.zte.zudp.common.persistence.entity.page.Page;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-06.
 */
public class SQLUtils {

    public static final String DATABASE_MYSQL = "mysql";
    public static final String DATABASE_ORACLE = "oracle";

    private static String databaseType = Configuration.getProperty("jdbc.type", DATABASE_MYSQL);

    public static Long queryTotalRecord(Connection connection, String sql, ParameterHandler parameterHandler) {
        ResultSet rs = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(rs);
        }

        return 0L;
    }

    public static String getCountSQL(String sql) {
        return "SELECT count(1) FROM (" + sql + ") AS total";
    }

    public static String getPageSQL(BoundSql boundSql, Page<?> page) {
        return getPageSQL(boundSql.getSql(), page);
    }

    public static String getPageSQL(String sql, Page<?> page) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        switch (databaseType) {
            case DATABASE_MYSQL:
                return getMysqlPageSQL(page, sqlBuffer);
            case DATABASE_ORACLE:
                return getOraclePageSQL(page, sqlBuffer);
            default:
                return getMysqlPageSQL(page, sqlBuffer);
        }
    }

    private static String getMysqlPageSQL(Page<?> page, StringBuffer sqlBuffer) {
        int offset = (page.getCurrent() - 1) * page.getSize();
        sqlBuffer.append(" limit ").append(offset).append(",").append(page.getSize());
        return sqlBuffer.toString();
    }

    private static String getOraclePageSQL(Page<?> page, StringBuffer sqlBuffer) {
        throw new RuntimeException("The method 'getOraclePageSQL' not implement!");
    }
}
