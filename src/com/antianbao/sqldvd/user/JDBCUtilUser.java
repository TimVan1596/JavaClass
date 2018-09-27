package com.antianbao.sqldvd.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * 链接数据库
 */
public class JDBCUtilUser {
    //加载驱动
    static {
        try {
            // 用来检查给定的类名存不存在
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动不存在，请添加驱动包！");
        }
    }

    /**
     * 关系：PreparedStatement继承自Statement,都是接口
     * 区别：PreparedStatement可以使用占位符，是预编译的，批处理比Statement效率高
     */
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    /**
     * 获得数据库连接对象Connection
     */
    private void openConnection() {
        //位置
        String DB_URL = "jdbc:mysql://" +
                "localhost:3306" +
                "/sqldvd" +
                "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8";
        //账号
        String USER = "root";
        //密码
        String PASS = "";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得PreparedStatement
     */
    public PreparedStatement getPrepareStatement(String sql) {
        openConnection();
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * 释放资源
     */
    public void close() {
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户注册
     */
    public int addStu(User stu) {
        int rlt = 0;
        List<User> list = queryStu();
        for (User ls : list) {
            if (ls.getName().equals(stu.getName())) {
                return rlt;
            }
        }
        String sql = "insert into User(name,password,phone) values(?,?,?)";
        PreparedStatement pstmt = getPrepareStatement(sql);
        try {
            pstmt.setString(1, stu.getName());
            pstmt.setString(2, stu.getPassword());
            pstmt.setString(3, stu.getPhone());
            rlt = pstmt.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 判断输入账号密码是否正确
     */
    public int isReally(String name, String password) {
        int rlt = 0;
        List<User> list = queryStu();
        for (User ls : list) {
            if (ls.getName().equals(name) && ls.getPassword().equals(password)) {
                return 1;
            }
        }
        return rlt;
    }
    /**
     * 找回密码：手机号
     */
    public int isPhone(String name, String phone) {
        int rlt = 0;
        List<User> list = queryStu();
        for (User ls : list) {
            if (ls.getName().equals(name) && ls.getPhone().equals(phone)) {
                return 1;
            }
        }
        return rlt;
    }

    /**
     * 修改密码
     */
    public int updateStu(String name, String password) {
        int rlt = 0;
        try {
            String sql = "update user SET password = ? where name = ?";
            PreparedStatement pstat = getPrepareStatement(sql);
            Object[] params = {password, name};
            for (int i = 1; i <= params.length; i++) {
                pstat.setObject(i, params[i - 1]);
            }
            rlt = pstat.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 将数据库中用户信息转为集合
     */
    public List<User> queryStu() {
        List<User> list = new ArrayList<User>();
        String sql = "select *from User";
        PreparedStatement pstat = getPrepareStatement(sql);
        try {
            ResultSet rs = pstat.executeQuery();
            User bd = null;
            while (rs.next()) {
                bd = new User();
                bd.setName(rs.getString("name"));
                bd.setPassword(rs.getString("password"));
                bd.setPhone(rs.getString("phone"));
                list.add(bd);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
