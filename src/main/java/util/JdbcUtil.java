package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author yaowenhao
 * @Title: JdbcUtil
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/26 23:14
 */
public class JdbcUtil {
    private static DataSource dataSource = new ComboPooledDataSource();

    /**
     * 它为null表示没有事务
     * 它不为null表示有事务
     * 当开启事务时，需要给它赋值
     * 当结束事务时，需要给它赋值为null
     * 并且在开启事务时，让dao的多个方法共享这个Connection
     */
    private static ThreadLocal<Connection> con = new ThreadLocal<Connection>();

    public static DataSource getDataSouce() {
        return dataSource;
    }

    /**
     * dao使用该方法获取连接
     *
     * @return 如果有事务，返回当前事务的con，如果没有事务，通过连接池返回新的con
     * @throws SQLException
     * @Title: getConnection
     * @Description: TODO
     * @return: Connection
     */
    public static Connection getConnection() throws SQLException {
//		获取当前线程的事务连接
        Connection connection = con.get();
        if (connection != null){
            return connection;
        }
        return dataSource.getConnection();
    }

    public static void beginTransaction() throws SQLException {
//		获取当前线程的事务连接
        Connection connection = con.get();
        if (connection != null){
            throw new SQLException("Transaction is already opened");
        }
//		给connection赋值，表示开启了事务
        connection = dataSource.getConnection();
//		设置为手动提交
        connection.setAutoCommit(false);
//		将当前事务连接放到con中
        con.set(connection);
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     * @Title: beginTrascation
     * @Description: TODO
     * @return: void
     */
    public static void commitTrascation() throws SQLException {
//		获取当前线程的事务连接
        Connection connection = con.get();
        if (connection == null) {
            System.out.println("没有事务，不能提交");
        }
//		提交事务
        connection.commit();
//		关闭连接
        connection.close();
//		表示事务结束！
        connection = null;
        con.remove();
    }

    public static void rollTrasaction() throws SQLException {
//		获取当前线程的事务连接
        Connection connection = con.get();
        if (connection == null) {
            System.out.println("没有事务不能回滚");
        }
        connection.rollback();
        connection.close();
        connection = null;
        con.remove();
    }

    public static void releaseConnection(Connection connection) throws SQLException {
//		获取当前线程的事务连接
        Connection conn = con.get();
        if (connection != conn) {
//			如果参数连接，与当前事务连接不同，说明这个连接不是当前事务，可以关闭！
            if (connection != null && !connection.isClosed()) {
//				如果参数连接没有关闭，关闭之！
                connection.close();
            }
        }
    }
}
