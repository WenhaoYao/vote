package util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author yaowenhao
 * @Title: QueryUtil
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/26 23:20
 */
public class QueryUtil extends QueryRunner {

    @Override
    public int[] batch(String sql, Object[][] params) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        int[] result = super.batch(connection, sql, params);
        JdbcUtil.releaseConnection(connection);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        T result = super.query(connection, sql, rsh, params);
        JdbcUtil.releaseConnection(connection);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        T result = super.query(connection, sql, rsh);
        JdbcUtil.releaseConnection(connection);
        return result;
    }

    @Override
    public int update(String sql) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        int result = super.update(connection, sql);
        JdbcUtil.releaseConnection(connection);
        return result;
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        int result = super.update(connection, sql, params);
        JdbcUtil.releaseConnection(connection);
        return result;
    }


    @Override
    public int update(String sql, Object param) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        int result = super.update(connection, sql, param);
        JdbcUtil.releaseConnection(connection);
        return result;
    }

}
