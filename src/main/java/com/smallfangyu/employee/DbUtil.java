package com.smallfangyu.employee;
import java.sql.*;


public class DbUtil {
    /** JDBC 驱动名及数据库 URL
     *
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dvdsytem?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8";

    /**数据库的用户名与密码，需要根据自己的设置
     *
     */
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            //增
            String [] params={"JAVA","哎哎"};
            String sql1="INSERT INTO dvd(name,state) VALUES(?,?)";
            PreparedStatement pstat = conn.prepareStatement(sql1);
              for (int i = 1; i <= params.length; i++) {
                     pstat.setObject(i, params[i - 1]);
                }
             pstat.executeUpdate();
             pstat.close();
             //删
            String sql2="DELETE FROM dvd WHERE no=1015";
            PreparedStatement pstat1 = conn.prepareStatement(sql2);
            pstat1.executeUpdate(sql2);
            pstat1.close();
            //改
            String sql3="UPDATE dvd SET name='nb666' WHERE no=1005";
            PreparedStatement pstat2 = conn.prepareStatement(sql3);
            pstat2.executeUpdate();
            pstat2.close();
            //查
            String sql = "SELECT * FROM dvd";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int no  = rs.getInt("no");
                String name = rs.getString("name");
                String state = rs.getString("state");

                // 输出数据
                System.out.print("ID: " + no);
                System.out.print(", dvd名称: " + name);
                System.out.print(", dvd状态: " + state);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) {stmt.close();}
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) {conn.close();}
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
