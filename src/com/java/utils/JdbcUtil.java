package com.java.utils;
import java.sql.*;
/**
 * @Author shaonaiyi@163.com
 * @Date 2022/3/6 12:40
 * @Description 数据库连接工具类
 */
public class JdbcUtil {

    /**
     * 1、获取Connection
     * @return Connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gzh?serverTimezone=UTC","root", "root");

        return connection;

    }

    /**
     * 2、释放资源
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) throws Exception {
//        Connection connection = JdbcUtil.getConnection();
//        System.out.println(connection);
//    }

}