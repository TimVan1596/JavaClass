package com.antianbao.webtest;

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
     * 判断输入账号密码是否正确
     */
    public int isReally(String name, String password) {
        int rlt = 0;
        List<User> list = queryStu();
        for (User ls : list) {
            if (ls.getUsername().equals(name) && ls.getUserpassword().equals(password)) {
                return 1;
            }
        }
        return rlt;
    }

    /**
     * 将数据库中用户信息转为集合（查询）
     */
    public List<User> queryStu() {
        List<User> list = new ArrayList<User>();
        String sql = "SELECT *FROM webtestuser";
        conn=getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            User bd = null;
            while (rs.next()) {
                bd = new User();
                bd.setUsername(rs.getString("username"));
                bd.setUserpassword(rs.getString("userpassword"));
                list.add(bd);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
