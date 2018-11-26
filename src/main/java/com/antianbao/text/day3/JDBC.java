package com.antianbao.text.day3;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author 安天宝
 * JAVA一班
 * 11月26日
 * Properties储存数据
 * 阿里巴巴连接池druid链接数据库
 */
public class JDBC {

    private static DruidDataSource druidDataSource = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    //初始化JDBC-MySQL连接(只做一次)
    static{
        //初始化数据库连接配置
        Properties properties = new Properties();

        try {
            properties.load(JDBC.class.getClassLoader()
                    .getResourceAsStream("atbTest.properties"));
            druidDataSource = (DruidDataSource) DruidDataSourceFactory
                    .createDataSource(properties);
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
//    public int addStu(attendance stu) {
//        int rlt = 0;
//        conn=getConn();
//        String sql = "insert into User(email,password) values(?,?)";
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, stu.getNo());
//            pstmt.setString(2, stu.getName());
//            pstmt.setString(3, stu.getTime());
//            pstmt.setString(4, stu.getState());
//            pstmt.setString(5, stu.getNote());
//            pstmt.setString(6, stu.getDate());
//            rlt = pstmt.executeUpdate();
//            close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        return rlt;
//    }

    /**
     * 修改密码
     */
    public int updateStu(String name, String password) {
        int rlt = 0;
        conn=getConn();
        try {
            String sql = "update user SET password = ? where email = ?";
            pstmt = conn.prepareStatement(sql);
            Object[] params = {password, name};
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
    public List<attendance> queryStu() {
        List<attendance> list = new ArrayList<attendance>();
        String sql = "SELECT *FROM timesheets";
        conn=getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            attendance bd = null;
            while (rs.next()) {
                bd = new attendance();
                bd.setNo(rs.getInt("no"));
                bd.setName(rs.getString("name"));
                bd.setTime(rs.getString("time"));
                bd.setState(rs.getString("state"));
                bd.setNote(rs.getString("note"));
                bd.setDate(rs.getString("date"));
                list.add(bd);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
