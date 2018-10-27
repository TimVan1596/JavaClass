package com.timvanx.biggerdvd.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * 基于 Druid 的 MySQL 数据库连接池
 * 封装MySQL数据库的工具类
 * <p>包含常用的select、update、insert、delete、count操作</p>
 * <p>含有sql方法，可以直接运行MySQL脚本（未完美测试，支持select）</p>
 * <ul>本JDBC操作数据库的步骤
 * <li>注册驱动 (只做一次)</li>
 * <li>建立连接(Connection)</li>
 * <li>创建执行SQL的语句(Statement)</li>
 * <li>执行语句并处理执行结果(ResultSet)</li>
 * <li>释放资源</li>
 * </ul>
 *
 * @author TimVan
 * @date 2018年10月27日17:42:47
 * @since 4.0
 */
public class JDBCUtil {


    static DruidDataSource dataSource;

    static {
        //初始化Druid-MySQL连接(只做一次)

        Properties properties = new Properties();
        try {

            String propFileName = "ftmdb.properties";

            properties.load(JDBCUtil.class.getClassLoader()
                    .getResourceAsStream(propFileName));
            dataSource = (DruidDataSource) DruidDataSourceFactory
                    .createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询数据
     *
     * @param tableName  表名
     * @param tableField 字段
     * @param tableWhere 条件
     * @param tableOrder 排序
     * @param tableLimit 选取
     * @return List<List < String>> 返回二维数组
     */
    public static List<List<String>> select
    (String tableName, ArrayList<String> tableField
            , String tableWhere, String tableOrder, String tableLimit) {
        Connection conn = JDBCUtil.getConnection();

        List<List<String>> list = new ArrayList<>();

        try {

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
            //System.out.println(sql);
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
        }

        return list;
    }

    /**
     * 更新数据
     *
     * @param tableName  数据表
     * @param updateData Map容器 field-value格式 数据数组
     *                   p.s 特别注意value是字符串时加引号!!!
     *                   如: updateData.put("password", "'123'");
     * @param tableWhere 过滤条件
     * @return int 返回受影响的记录条数(失败返回-1)
     */
    public static int update(String tableName
            , Map<String, Object> updateData
            , String tableWhere) {
        Connection conn = JDBCUtil.getConnection();
        //返回值：返回受影响的记录条数(失败返回-1)
        int affectRowCNT = -1;
        try {

            //构建基于PreparedStatement的SQL语句
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" update ");
            stringBuilder.append(tableName);
            stringBuilder.append(" set ");
            //填充字段名和值赋值
            boolean isFirst = true;
            for (Map.Entry<String, Object> entry
                    : updateData.entrySet()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stringBuilder.append(" , ");
                }
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                Object valObj = entry.getValue();
                //拼接数据
                stringBuilder.append(objectToString(valObj));

            }

            //where条件子句
            if (tableWhere != null) {
                stringBuilder.append(" where ");
                stringBuilder.append(tableWhere);
            }


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
        }

        return affectRowCNT;
    }

    /**
     * 插入数据
     *
     * @param tableName  数据表
     * @param insertData Map容器 field-value格式 数据数组
     *                   p.s 特别注意value的类型根据String还是Number自动转换
     *                   如: insertData.put("password", "123");
     * @return int 返回受影响的记录条数(失败返回-1)
     */
    public static int insert(String tableName
            , Map<String, Object> insertData) {
        Connection conn = JDBCUtil.getConnection();
        //返回值：返回受影响的记录条数(失败返回-1)
        int affectRowCNT = -1;

        try {
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

            //将value中内容拼接
            boolean isFirst = true;
            for (Object object : insertData.values()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stringBuilder.append(" , ");
                }
                //拼接数据
                stringBuilder.append(objectToString(object));

            }
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
        }

        return affectRowCNT;
    }

    /**
     * 删除数据
     *
     * @param tableName  数据表
     * @param tableWhere 过滤条件
     * @return int 返回受影响的记录条数(失败返回-1)
     */
    public static int delete(String tableName, String tableWhere) {
        Connection conn = JDBCUtil.getConnection();
        //返回值：返回受影响的记录条数(失败返回-1)
        int affectRowCNT = -1;

        try {

            //构建基于PreparedStatement的SQL语句
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" delete from ");
            stringBuilder.append(tableName);

            //where条件子句
            if (tableWhere != null) {
                stringBuilder.append(" where ");
                stringBuilder.append(tableWhere);
            }


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
        }

        return affectRowCNT;
    }


    /**
     * 聚合函数count
     *
     * @param tableName  数据表
     * @param tableField 记录字段名(注意这里只能一个字段名)
     * @param tableWhere 过滤条件
     * @return int 计算符合条件的字段个数
     */
    public static int count(String tableName, String tableField
            , String tableWhere) {
        Connection conn = JDBCUtil.getConnection();
        //返回值：符合条件的字段个数
        int rowCount = 0;

        try {

            //构建基于PreparedStatement的SQL语句
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" select count( ");
            stringBuilder.append(tableField);
            stringBuilder.append(" ) from ");
            stringBuilder.append(tableName);

            //where条件子句
            if (tableWhere != null) {
                stringBuilder.append(" where ");
                stringBuilder.append(tableWhere);
            }


            String sql = stringBuilder.toString();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //select使用 executeUpdate ，只会返回影响行数
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                rowCount = rs.getInt(1);
            }

            // 完成后关闭
            preparedStatement.close();
            conn.close();
            rs.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        return rowCount;
    }

    /**
     * TODO:该方法复杂度较高，只完美支持select查询，其他未测试
     * !!!警告   自定义方法   警告!!!
     *
     * @param script 自定义操作脚本语句
     * @return array $jarr 返回值数组
     */
    public static List<List<String>> sql4Select(String script) {
        Connection conn = JDBCUtil.getConnection();

        List<List<String>> list = new ArrayList<>();

        try {

            //构建基于PreparedStatement的SQL语句
            PreparedStatement preparedStatement = conn.prepareStatement(script);

            //select 查询使用 executeQuery ，只会返回 ResultSet
            ResultSet rs = preparedStatement.executeQuery();
            //获得结果集结构信息,元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //获得列数
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                //构建行组
                ArrayList<String> row = new ArrayList<>();

                for (int i = 1; i <= columnCount; i++) {
                    row.add((String) rs.getObject(i));
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
        }
        return list;
    }

    /**
     * TODO:该方法复杂度较高，其他未测试
     * !!!警告   自定义方法（除select查询）   警告!!!
     *
     * @param script 自定义操作脚本语句
     * @return 返回受影响的记录条数(失败返回-1)
     */
    public static int sql(String script) {
        Connection conn = JDBCUtil.getConnection();

        List<List<String>> list = new ArrayList<>();
        //返回值：返回受影响的记录条数(失败返回-1)
        int affectRowCNT = -1;

        try {

            //构建基于PreparedStatement的SQL语句
            PreparedStatement preparedStatement = conn.prepareStatement(script);

            //非select使用 executeUpdate ，只会返回影响行数
            affectRowCNT = preparedStatement.executeUpdate();


            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return affectRowCNT;
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

    /**
     * 数据库中将Object对象插入数据库
     * 如插入String类型的值 "hello" 自动转为 "'hello'"
     * 插入数字 123 转为 "123"
     *
     * @param object
     * @return 返回StringBuilder字符串
     */
    private static StringBuilder objectToString(Object object) {
        StringBuilder stringBuilder = new StringBuilder();
        if (object instanceof String) {
            stringBuilder.append("'");
            stringBuilder.append(object);
            stringBuilder.append("'");
        } else if (object instanceof Number) {
            stringBuilder.append(object);
        }
        return stringBuilder;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void colse() {
        dataSource.close();
    }

}

