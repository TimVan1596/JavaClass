package com.zhangmin.javawebDVDservlet;

import java.sql.*;

public class DBHelp {
    private Connection conn;
    private Statement sta;
    private PreparedStatement pre;
    //加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //打开连接
    private  void openConnection(){
        String url = "jdbc:mysql://localhost:3306/dvd?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8";
        String user = "root";
        String password = "123456";
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //获得Statement
    public  Statement getSta(){
        openConnection();
        try {
            sta = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sta;
    }

    //获得PreparedStatement
    public PreparedStatement getPre(String sql){
        openConnection();
        try {
            pre = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pre;
    }

    //关闭连接方法
    public void closeRescore(){
        try {
            if (pre != null && !pre.isClosed()) {
                pre.close();
            }
            if (sta != null && !sta.isClosed()) {
                sta.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
