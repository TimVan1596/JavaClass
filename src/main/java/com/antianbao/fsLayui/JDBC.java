package com.antianbao.fsLayui;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBC {
    /**
     * 关系：PreparedStatement继承自Statement,都是接口
     * 区别：PreparedStatement可以使用占位符，是预编译的，批处理比Statement效率高
     */
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * 获得数据库连接对象Connection
     */
    private void openConnection() {
        //初始化数据库连接配置
        Properties properties=new Properties();
        InputStream in = JDBC.class.getClassLoader().getResourceAsStream("atbTest.properties");
        //读取db.properties
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //赋值
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String driverClassName = properties.getProperty("driverClassName");
        try {
            // 用来检查给定的类名存不存在
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动不存在，请添加驱动包！");
        }
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得PreparedStatement
     */
    private PreparedStatement getPrepareStatement(String sql) {
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
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     * @return
     */
    public List<fsLayuiUser> findPage(int page, int limit, String id, String name
            , String birthdayStart, String birthdayEnd,String field,String order){
        List<fsLayuiUser> list = new ArrayList<>();
        String sql = "SELECT *FROM fsLayuiUser " +
                "WHERE id like '%"+id+"%' AND name like '%"+name+"%' " +
                "AND birthday > '"+birthdayStart+"' AND birthday < '"+birthdayEnd+"' " +
                "ORDER BY "+field+" "+order+" " +
                "LIMIT "+(page-1)*limit+","+limit+"";
        PreparedStatement pstat = getPrepareStatement(sql);
        try {
            rs = pstat.executeQuery();
            fsLayuiUser bd;
            while (rs.next()) {
                bd = new fsLayuiUser();
                bd.setId(rs.getInt("id"));
                bd.setName(rs.getString("name"));
                bd.setSex(rs.getString("sex"));
                bd.setAreal(rs.getString("areal"));
                bd.setType(rs.getString("type"));
                bd.setCity(rs.getString("city"));
                bd.setProvince(rs.getString("province"));
                bd.setBirthday(rs.getString("birthday"));
                bd.setDescribe(rs.getString("describe"));
                list.add(bd);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询数据总数
     * @return
     */
    public int findCountPage(String id,String name,String birthdayStart,String birthdayEnd){
        int count = 0;
        String sql = "select count(*) from fsLayuiUser " +
                "WHERE id like '%"+id+"%' AND name like '%"+name+"%' "
                + "AND birthday > '"+birthdayStart+"' AND birthday < '"+birthdayEnd+"' ";
        PreparedStatement pstmt = getPrepareStatement(sql);
        try {
            rs = pstmt.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
            close();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return count;
    }

}
