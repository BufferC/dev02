package com.fc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// 封装的JDBC工具类
public class DruidUtil {
    // Druid连接池提供的数据源
    private static DataSource dataSource = null;
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    // 使用静态代码块对成员进行初始化
    static {
        // 从Properties文件中获取参数
        Properties properties = new Properties();

        try {
            // 通过反射加载指定位置下的配置文件
            properties.load(DruidUtil.class.getResourceAsStream("/druid.properties"));

            // 获取Druid连接池的数据源
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection() {
        try {
            // 通过数据源来获取连接
            connection = dataSource.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return connection;
    }

    // 获取搬运工对象
    private static Statement getStatement() {
        // 防止连接为空导致的空指针
        if (connection == null) {
            connection = getConnection();
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return statement;
    }

    // 增删改
    public static int executeUpdate(String sql) {
        if (statement == null) {
            statement = getStatement();
        }

        // 受影响的行数默认为0
        int affectedRows = 0;

        try {
            affectedRows = statement.executeUpdate(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            close(resultSet, statement, connection);
        }

        return affectedRows;
    }

    // 查询
    public static ResultSet executeQuery(String sql) {
        try {
            resultSet = getStatement().executeQuery(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return resultSet;
    }

    public static void close(Connection connection) {
        close(resultSet, statement, connection);
    }

    public static void close(ResultSet resultSet) {
        close(resultSet, statement, connection);
    }

    public static void close(Statement statement, Connection connection) {
        close(resultSet, statement, connection);
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
