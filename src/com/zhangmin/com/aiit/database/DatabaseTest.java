package com.zhangmin.com.aiit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseTest {
    public static void main(String[] args){
        //1.加载驱动


        try {
            Class.forName("com.mysql.jdbc.Driver");


            //2.创建连接
            Connection conn;
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stu"
                            + "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8"
                    ,"root","123456");
            //3.准备sql
            String sql = "INSERT INTO tb_student(stuName,stuAddress,stuScore) value ('HH','安徽信息工程学院',70)";
            //4.执行处理
            PreparedStatement pre = conn.prepareStatement(sql);
            //5.返回结果
            int ret = pre.executeUpdate();
            if(ret>=0){
                System.out.println("执行成功");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
