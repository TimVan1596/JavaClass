package com.timvanx.exam;


import java.sql.*;


public class BookJDBC {

    private static String DB_URL;
    private static String USER;
    private static String PASS;

    static {
        init();
    }


    /**
     * 初始化JDBC-MySQL连接(只做一次)
     * <ul>
     * <li>初始化数据库连接配置（.properties）</li>
     * <li>注册连接</li>
     * </ul>
     */
    private static void init() {

        DB_URL = "jdbc:mysql://127.0.0.1:3306/biggerdvd?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8";
        USER = "homework";
        PASS = "homework";

        // 注册 JDBC 驱动
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //查询价格在50元到100元（含边界）
    public static void select50To100() {
        Connection conn = null;


        try {
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //构建基于PreparedStatement的SQL语句
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" select ");
            stringBuilder.append(" booktitle,price ");
            stringBuilder.append(" from ");
            stringBuilder.append("bookinfo");
            stringBuilder.append(" where (price-49.999 >0) " +
                    "and (100.001 - price >0)");


            String sql = stringBuilder.toString();
//            System.out.println(sql);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //select 查询使用 executeQuery ，只会返回 ResultSet
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String booktitle = rs.getString(1);
                String price = rs.getString(2);

                System.out.println("书名:" + booktitle
                        + "  价格：" + price);

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

    }

    // 统计“文学”的总价
    public static void sumPriceOfLiteray() {
        Connection conn = null;


        try {
            //连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //构建基于PreparedStatement的SQL语句
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select booktype.typename,sum(price)\n" + "from bookinfo , booktype\n" +
                    "where bookinfo.typeid = booktype.typeid\n" +
                    "  and booktype.typename = '文学'");


            String sql = stringBuilder.toString();
//            System.out.println(sql);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //select 查询使用 executeQuery ，只会返回 ResultSet
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String booktitle = rs.getString(1);
                String price = rs.getString(2);

                System.out.println("种类:" + booktitle
                        + "  总价：" + price);

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

    }

    public static void main(String[] args) {
        //查询价格在50元到100元（含边界）
        System.out.println("查询价格在50元到100元（含边界）");
        select50To100();

        //统计“文学”的总价
        System.out.println("\n\n 统计“文学”的总价");
        sumPriceOfLiteray();
    }

}
