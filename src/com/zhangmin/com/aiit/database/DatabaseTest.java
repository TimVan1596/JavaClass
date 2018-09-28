package com.zhangmin.com.aiit.database;

import java.sql.*;


public class DatabaseTest {
    public static void main(String[] args){
        //1.加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.创建连接
            Connection conn;
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stu"
                            + "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8"
                    ,"root","123456");
            //3.准备sql
            //String sql1 = "INSERT INTO tb_student(stuName,stuAddress,stuScore) value ('HH','安徽信息工程学院',70)";
            String sql2 = "SELECT stuID,stuName,stuAddress,stuScore FROM tb_student";
            //String sql3 ="UPDATE tb_student set stuScore = stuScore+1 where stuName='张三'";
            //String sql4 = "DELETE FROM tb_student where stuName = 'HH'";
            //4.执行处理
            PreparedStatement pre;
            // pre= conn.prepareStatement(sql1);//增
              pre = conn.prepareStatement(sql2);//查
             //pre = conn.prepareStatement(sql3);//改
               //pre = conn.prepareStatement(sql4);//删
            /*
            * 查询操作
            */
             ResultSet rs = pre.executeQuery();
            System.out.println("序号"+"\t"+"姓名"+"\t"+"地址"+"\t"+"分数");
             while (rs.next()){
                 int id = rs.getInt("stuID");
                 String name = rs.getString("stuName");
                 String address = rs.getString("stuAddress");
                 float score = rs.getFloat("stuScore");
                 System.out.println(id+"\t"+name+"\t"+address+"\t"+score);
                 //System.out.println("查询到结果"+);
             }
            //5.返回结果
            /*
            * 插入、修改、删除操作
            */
//            int ret = pre.executeUpdate();
//            if(ret>=0){
//                System.out.println("执行成功");
//            }
            //6.关闭连接
             rs.close();
             pre.close();
             conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
