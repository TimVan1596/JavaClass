package com.antianbao.javaWebDvdLayui.tool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.antianbao.javaWebDvdLayui.table.Dvd;

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
                    .getResourceAsStream("atbdb.properties"));
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
     * 查询数据
     * @return
     */
    public List<Dvd> findPage(int page, int limit, String no, String name, String entranceStart, String entranceEnd){
        List<Dvd> list = new ArrayList<>();
        conn=getConn();
        String sql = "SELECT *FROM dvd " +
                "WHERE no like '%"+no+"%' AND name like '%"+name+"%' " +
//                "AND time > '"+entranceStart+"' AND time < '"+entranceEnd+"' " +
                "LIMIT "+(page-1)*limit+","+limit+"";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Dvd bd;
            while (rs.next()) {
                bd = new Dvd();
                bd.setNo(rs.getInt("no"));
                bd.setImage(rs.getString("image"));
                bd.setName(rs.getString("name"));
                bd.setState(rs.getInt("state"));
                bd.setBorrow(rs.getInt("borrow"));
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
    public int findCountPage(String no,String name,String entranceStart,String entranceEnd){
        int count = 0;
        conn=getConn();
        String sql = "select count(*) from dvd " +
                "WHERE no like '%"+no+"%' AND name like '%"+name+"%' "
//                + "AND time > '"+entranceStart+"' AND time < '"+entranceEnd+"' "
                ;
        try {
            pstmt = conn.prepareStatement(sql);
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
