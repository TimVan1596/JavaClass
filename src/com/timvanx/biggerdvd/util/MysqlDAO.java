package com.timvanx.biggerdvd.util;

import java.sql.*;

/**
 * 封装MySQL数据库的工具类
 * <p>包含常用的update、select、delete操作</p>
 * <ul>本JDBC操作数据库的步骤
 *  <li>注册驱动 (只做一次)</li>
 *  <li>建立连接(Connection)</li>
 *  <li>创建执行SQL的语句(Statement)</li>
 *  <li>执行语句并处理执行结果(ResultSet)</li>
 *  <li>释放资源</li>
 * </ul>
 * @author TimVan
 * @date 2018-9-17 17:12:03
 * @since 4.0
 */
public class MysqlDAO {

    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * 设置url ，用户名， 密码
     */
    static String DB_URL;
    static String USER;
    static String PASS;

    /**
     * 初始化JDBC-MySQL连接(只做一次)
     * <ul>
     * <li>注册连接</li>
     * <li>初始化数据库连接配置（.properties）</li>
     * </ul>
     *
     * @throws
     */
    private static void init() throws ClassNotFoundException {
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);

        DB_URL =
                "jdbc:mysql://" +
                        "120.79.210.170:3306" +
                        "/biggerdvd" +
                        "?serverTimezone=UTC";
        USER = "homework";
        PASS = "homework";

    }

    /**
     * 释放资源
     */
    private static void close(Connection conn, Statement stmt) {
        // 关闭资源
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException se2) {
        }// 什么都不做
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * select MySQL方法
     *
     * @param tableName 表名
     */
    public static void select(String tableName) {
        Connection conn = null;
        Statement stmt = null;

        try {
            init();

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT id,name,password " +
                    " FROM " +
                    tableName;
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString
                        ("password");

                //输出数据
                System.out.print("id: " + id);
                System.out.print(", 用户名: " + name);
                System.out.print(", 密码: " + password);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            //释放资源
            close(conn, stmt);
        }
    }

    public static void main(String[] args) {
        select("account");
    }


}

