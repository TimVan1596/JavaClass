package com.antianbao.mysql920;

import java.sql.*;
/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * 链接数据库
 */
public class DBHelper {
    static {
        try {
            // 用来检查给定的类名存不存在
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动不存在，请添加驱动包！");
        }
    }
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    private void openConnection(){
        String DB_URL =
                "jdbc:mysql://" +
                        "localhost:3306" +
                        "/test" +
                        "?serverTimezone=UTC";
        String USER = "root";
        String PASS = "";

        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Statement getStatment() {
        openConnection();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public PreparedStatement getPrepareStatement(String sql) {
        openConnection();
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    public void close() {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
