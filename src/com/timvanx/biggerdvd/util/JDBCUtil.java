package com.timvanx.biggerdvd.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * 封装MySQL数据库的工具类
 * <p>包含常用的update、select、delete操作</p>
 * <ul>本JDBC操作数据库的步骤
 * <li>注册驱动 (只做一次)</li>
 * <li>建立连接(Connection)</li>
 * <li>创建执行SQL的语句(Statement)</li>
 * <li>执行语句并处理执行结果(ResultSet)</li>
 * <li>释放资源</li>
 * </ul>
 *
 * @author TimVan
 * @date 2018-9-17 17:12:03
 * @since 4.0
 */
public class JDBCUtil {
    /**
     * 设置驱动、url、用户名、密码
     * 通过db.properties配置
     */
    private static String DB_URL;
    private static String USER;
    private static String PASS;

    static {
        //初始化JDBC-MySQL连接(只做一次)
        init();
    }

    /**
     * 更新数据
     *
     * @param tableName  数据表
     * @param updateData Map容器 field-value格式 数据数组
     *                   p.s 特别注意value是字符串时加引号!!!
     *                   如: updateData.put("password", "'123'");
     * @param tableWhere 过滤条件
     * @return mixed 受影响记录
     */
    public static int update(String tableName
            , Map<String, String> updateData
            , String tableWhere) {
        Connection conn = null;
        //返回值：返回受影响的记录条数(失败返回-1)
        int affectRowCNT = -1;
        try {
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //构建基于PreparedStatement的SQL语句
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" update ");
            stringBuilder.append(tableName);
            stringBuilder.append(" set ");
            //填充字段名和值赋值
            boolean isFirst = true;
            for (Map.Entry<String, String> entry
                    : updateData.entrySet()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stringBuilder.append(" , ");
                }
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(entry.getValue());
            }

            //where条件子句
            if (tableWhere != null) {
                stringBuilder.append(" where ");
                stringBuilder.append(tableWhere);
            }


            String sql = stringBuilder.toString();
            System.out.println("-" + sql + "-");
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //非select使用 executeUpdate ，只会返回影响行数
            affectRowCNT = preparedStatement.executeUpdate();


            // 完成后关闭
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            //释放资源
            close(conn);
        }

        return affectRowCNT;
    }


    /**
     * 查询数据
     * @param tableName  表名
     * @param tableField 字段
     * @param tableWhere 条件
     * @param  tableOrder 排序
     * @param  tableLimit 选取
     */
    public static List<List<String>> select
    (String tableName, ArrayList<String> tableField
            , String tableWhere, String tableOrder, String tableLimit) {
        Connection conn = null;

        List<List<String>> list = new ArrayList<>();

        try {
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //构建基于PreparedStatement的SQL语句
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" select ");
            stringBuilder.append(arrayListToPreparedStm(tableField));
            stringBuilder.append(" from ");
            stringBuilder.append(tableName);
            //where条件子句
            if (tableWhere != null) {
                stringBuilder.append(" where ");
                stringBuilder.append(tableWhere);
            }
            //order条件子句
            if (tableOrder != null) {
                stringBuilder.append(" order by ");
                stringBuilder.append(tableOrder);
            }
            //limit条件子句
            if (tableLimit != null) {
                stringBuilder.append(" limit ");
                stringBuilder.append(tableLimit);
            }
            String sql = stringBuilder.toString();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //select 查询使用 executeQuery ，只会返回 ResultSet
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                //构建行组
                ArrayList<String> row = new ArrayList<>();
                for (String fieldName : tableField) {
                    String filedData = rs.getString(fieldName);
                    row.add(filedData);
                }
                list.add(row);
            }

            // 完成后关闭
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            //释放资源
            close(conn);
        }

        return list;
    }

    /**
     * 插入数据
     *
     * @param tableName  数据表
     * @param insertData Map容器 field-value格式 数据数组
     *                   p.s 特别注意value是字符串时加引号!!!
     *                   如: insertData.put("password", "'123'");
     * @return int 返回受影响的记录条数(失败返回-1)
     */
    public static int insert(String tableName
            , Map<String, String> insertData) {
        Connection conn = null;
        //返回值：返回受影响的记录条数(失败返回-1)
        int affectRowCNT = -1;

        try {
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //构建基于PreparedStatement的SQL语句
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" insert into ");
            stringBuilder.append(tableName);
            stringBuilder.append(" ( ");
            //填充字段名
            ArrayList<String> filedList =
                    new ArrayList<>(insertData.keySet());
            stringBuilder.append(
                    arrayListToPreparedStm(filedList));
            stringBuilder.append(" ) value ( ");
            //填充数据值
            ArrayList<String> valueList =
                    new ArrayList<>(insertData.values());
            stringBuilder.append(
                    arrayListToPreparedStm(valueList));
            stringBuilder.append(" ) ");

            String sql = stringBuilder.toString();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //非select使用 executeUpdate ，只会返回影响行数
            affectRowCNT = preparedStatement.executeUpdate();


            // 完成后关闭
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            //释放资源
            close(conn);
        }

        return affectRowCNT;
    }

    /**
     * 初始化JDBC-MySQL连接(只做一次)
     * <ul>
     * <li>初始化数据库连接配置（.properties）</li>
     * <li>注册连接</li>
     * </ul>
     */
    private static void init() {
        //初始化数据库连接配置
        Properties properties = new Properties();
        InputStream in = JDBCUtil.class.getClassLoader()
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
         通过db.properties文件配置
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
    private static void close(Connection conn) {
        // 关闭资源
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    /**
     * 将ArrayList<String>转换成" ? ," 格式(PreparedStatement，SQL用)
     * 常用于SQL语句的字段
     * 如将ArrayList转换成 "?,?,?" 格式
     *
     * @param arrayList 原始ArrayList，保存各字段
     * @return StringBuilder 返回输出结果
     */
    private static StringBuilder arrayListToPreparedStm(ArrayList<String> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!arrayList.isEmpty()) {
            boolean isFirst = true;
            for (int i = 0; i < arrayList.size(); i++) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stringBuilder.append(" , ");
                }
                stringBuilder.append(arrayList.get(i));
            }
        }
        return stringBuilder;
    }


    public static void main(String[] args) {
        //设置数据表名
        String tableName = "account";


        //插入数据
        Map<String, String> insertData =
                new HashMap<String, String>(3);
        Calendar now = Calendar.getInstance();
        insertData.put("name", "'User"
                + now.get(Calendar.DAY_OF_MONTH)
                + "-"
                + now.get(Calendar.HOUR_OF_DAY)
                + "-"
                + now.get(Calendar.MINUTE)
                + "-"
                + now.get(Calendar.SECOND)
                + "'");
        insertData.put("password", "'123'");
        int affectRowCNT = insert(tableName, insertData);

        //更新数据
        //设置where子句
        String tableWhere = "name = 'admin'";
        Map<String, String> updateData =
                new HashMap<String, String>(3);
        updateData.put("password", "'20180926'");
        affectRowCNT = update(tableName,updateData,tableWhere);

        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("id");
            add("name");
            add("password");
        }};
        String tableOrder = " age desc ";
        String tableLimit = " 0,5 ";
        List<List<String>> list =
                select(tableName, tableField, null,tableOrder,tableLimit);
        //打印结果
        for (List<String> cache : list) {
            for (String data : cache) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }


    }


}

