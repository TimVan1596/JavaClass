package com.timvanx.biggerdvd.util;

import javax.sound.midi.SoundbankResource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
    /**
     设置驱动、url、用户名、密码
     通过db.properties配置
     */
    private static String DB_URL;
    private static String USER;
    private static String PASS;

    static {
        //初始化JDBC-MySQL连接(只做一次)
        init();
    }

    /**
     * 初始化JDBC-MySQL连接(只做一次)
     * <ul>
     *   <li>初始化数据库连接配置（.properties）</li>
     *   <li>注册连接</li>
     * </ul>
     */
    private static void init() {
        //初始化数据库连接配置
        Properties properties = new Properties();
        InputStream in = MysqlDAO.class.getClassLoader()
                .getResourceAsStream("db.properties");
        /**
         * 设置驱动、url、用户名、密码
         * 通过db.properties配置
         */


        try {
            properties.load(in);
        } catch (IOException e) {
            Constants.reportError("数据库配置文件");
            e.printStackTrace();
        }

        /**
         设置驱动、url、用户名、密码
         通过db.properties配置
         */
        String JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
        DB_URL = properties.getProperty("DB_URL");
        USER = properties.getProperty("USER");
        PASS = properties.getProperty("PASS");


        // 注册 JDBC 驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            Constants.reportError("JDBC驱动");
            e.printStackTrace();
        }

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
     * select - MySQL方法
     * @param tableName 表名
     * @param tableField 字段
     * @param tableWhere 条件
     */
    public static void select(String tableName, ArrayList<String> tableField, String tableWhere) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select ");
            stringBuilder.append(arrayListToString(tableField));
            stringBuilder.append(" from ");
            stringBuilder.append(tableName);
            stringBuilder.append(" where ");
            stringBuilder.append(tableWhere);
            String sql = stringBuilder.toString();

            System.out.println("---" + sql);

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

    /**
     * 将ArrayList<String>转换成String+"," 顿号格式
     * 常用于SQL语句的字段
     * 如将ArrayList转换成 "id,name,password" 格式
     *
     * @param arrayList 原始ArrayList，保存各字段
     * @return String 返回结果
     */
    private static String arrayListToString(ArrayList<String> arrayList) {
        StringBuilder stringBuffer = new StringBuilder();
        if (!arrayList.isEmpty()) {
            boolean isFirst = true;
            for (String field : arrayList) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stringBuffer.append(",");
                }
                stringBuffer.append(field);

            }
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String tableName = "account";
        ArrayList<String> tableField = new ArrayList<>();
        tableField.add("id");
        tableField.add("name");
        tableField.add("password");
        String tableWhere = "name = " + "'account'";
        select(tableName, tableField, tableWhere);
    }


}

