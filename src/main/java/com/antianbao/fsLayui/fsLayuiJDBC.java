package com.antianbao.fsLayui;

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
public class fsLayuiJDBC {

    private DruidDataSource druidDataSource = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /**
     *获取数据库连接
     * @return
     */
    public Connection getConn(){
        //初始化数据库连接配置
        Properties properties = new Properties();
        //获取数据库连接
        try {
            properties.load(fsLayuiJDBC.class.getClassLoader()
                    .getResourceAsStream("atbTest.properties"));
            druidDataSource = (DruidDataSource) DruidDataSourceFactory
                    .createDataSource(properties);
            return druidDataSource.getConnection();
        } catch (Exception e) {
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
            druidDataSource.close();
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
        conn=getConn();
        String sql = "SELECT *FROM fsLayuiUser " +
                "WHERE id like '%"+id+"%' AND name like '%"+name+"%' " +
                "AND birthday > '"+birthdayStart+"' AND birthday < '"+birthdayEnd+"' " +
                "ORDER BY "+field+" "+order+" " +
                "LIMIT "+(page-1)*limit+","+limit+"";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            fsLayuiUser bd;
            while (rs.next()) {
                bd = new fsLayuiUser();
                bd.setId(rs.getInt("id"));
                bd.setName(rs.getString("name"));
                bd.setSex(rs.getString("sex"));
                bd.setType(rs.getString("type"));
                bd.setAreal(rs.getString("areal"));
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
        conn=getConn();
        String sql = "select count(*) from fsLayuiUser " +
                "WHERE id like '%"+id+"%' AND name like '%"+name+"%' "
                + "AND birthday > '"+birthdayStart+"' AND birthday < '"+birthdayEnd+"' "
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
