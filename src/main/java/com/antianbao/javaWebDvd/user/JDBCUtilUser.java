package com.antianbao.javaWebDvd.user;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

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

    private static DruidDataSource druidDataSource = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    //初始化JDBC-MySQL连接(只做一次)
    static{
        //初始化数据库连接配置
        Properties properties = new Properties();

        try {
            properties.load(JDBCUtilUser.class.getClassLoader().getResourceAsStream("atbdb.properties"));
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *获取数据库连接
     * @return
     */
    public Connection getConn(){
        //获取数据库连接
        try {
            return druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     */
    public void close() {
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
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
        conn=getConn();
        String sql = "insert into User(email,password) values(?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
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
        conn=getConn();
        try {
            String sql = "update user SET password = ? where email = ?";
            pstmt = conn.prepareStatement(sql);
            Object[] params = {MD5(password), name};
            for (int i = 1; i <= params.length; i++) {
                pstmt.setObject(i, params[i - 1]);
            }
            rlt = pstmt.executeUpdate();
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
        conn=getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
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
