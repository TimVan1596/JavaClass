package com.antianbao.javaWebDvd.user;

import java.io.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.*;

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
    private static  String DB_URL;
    private static  String USER ;
    private static  String PASS ;

    /**
     * 获得数据库连接对象Connection
     */
    private void openConnection() {
        //初始化数据库连接配置
        Properties properties=new Properties();
        InputStream in = JDBCUtilUser.class.getClassLoader().getResourceAsStream("atbdb.properties");
        //读取db.properties
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //赋值
        DB_URL=properties.getProperty("DB_URL");
        USER=properties.getProperty("USER");
        PASS=properties.getProperty("PASS");
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
        }catch (SQLException e) {
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
            if (ls.getEmail().equals(stu.getEmail())) {
                return rlt;
            }
        }
        String sql = "insert into User(email,password) values(?,?)";
        PreparedStatement pstmt = getPrepareStatement(sql);
        try {
            pstmt.setString(1, stu.getEmail());
            pstmt.setString(2, MD5(stu.getPassword()));
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
        System.out.println(MD5(password));
        int rlt = 0;
        List<User> list = queryStu();
        for (User ls : list) {
            if (ls.getEmail().equals(name) && ls.getPassword().equals(MD5(password))) {
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
            String sql = "update user SET password = ? where email = ?";
            PreparedStatement pstat = getPrepareStatement(sql);
            Object[] params = {MD5(password), name};
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
                bd.setEmail(rs.getString("email"));
                bd.setPassword(rs.getString("password"));
                list.add(bd);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int panduan(String name) {
        int rlt = 0;
        List<User> list = queryStu();
        for (User ls : list) {
            if (ls.getEmail().equals(name)) {
                return 1;
            }
        }
        return rlt;
    }

    //MD5加密
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
