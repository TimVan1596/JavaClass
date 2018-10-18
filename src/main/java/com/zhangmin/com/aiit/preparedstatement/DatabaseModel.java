package com.zhangmin.com.aiit.preparedstatement;

import java.sql.*;

public class DatabaseModel {
    //1.加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //2.建立连接
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8";
        String user = "root";
        String password = "123456";
        Connection conn = null;
        {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
             return  conn;
    }
    //3.测试
    //将指定学生分数加+1
    public static void addScore(int stuID){
        Connection conn = getConnection();
        String sql = "update tb_student set stuScore = stuScore+1 where stuID=?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1,stuID);

            int iRet = pre.executeUpdate();
            if(iRet >= 0){
                System.out.println("执行成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
          addScore(3);
    }

}
