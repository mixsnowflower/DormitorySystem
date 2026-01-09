package com.dormitory.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Properties properties = new Properties();

    static {
        try {
            // 加载 db.properties 文件
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(in);
            // 加载驱动类
            Class.forName(properties.getProperty("driver"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库驱动加载失败！");
        }
    }

    // 2. 获取数据库连接
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取数据库连接失败！请检查账号密码或数据库名");
            return null;
        }
    }

    // 3. 释放资源
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("恭喜！数据库连接成功！");
            System.out.println("连接对象：" + conn);
        } else {
            System.out.println("连接失败，请检查 db.properties 配置");
        }
    }
}