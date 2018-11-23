package com.smallfangyu.data;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.*;
import java.util.Properties;

/**
 * @author fy
 */
public class JdbcDruid {

    private static DruidDataSource druidDataSource = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /**
     * 初始化JDBC-MySQL连接
     */
    public static void init() {
        //初始化数据库连接配置
        Properties properties = new Properties();

        //druid读取fydb.properties
        try {
            properties.load(JdbcDruid.class.getClassLoader().getResourceAsStream("fydb.properties"));
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
//            druidDataSource.setUrl(properties.getProperty("url"));
//            druidDataSource.setUsername(properties.getProperty("username"));
//            druidDataSource.setPassword(properties.getProperty("password"));
//            druidDataSource.setDriverClassName(properties.getProperty("driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static{
        //初始化JDBC-MySQL连接(只做一次)
        init();
    }

    /**
     *获取数据库连接
     * @return
     */
    public Connection getConn(){
        //获取数据库连接
        try {
            //if (conn == null || conn.isClosed()) {
                return druidDataSource.getConnection();
            //}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *更新数据库
     * @return
     */
    public int executeUpdate(String sql,Object[] params){
        int rlt=-1;
        conn=getConn();
        try {
             pstmt = conn.prepareStatement(sql);
            putParams(params, pstmt);
            rlt= pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 查询数据库
     * @param sql
     * @param params
     * @return
     */
    public ResultSet executeQuery(String sql,Object[] params){
        conn=getConn();
        try {
            pstmt =conn.prepareStatement(sql);
            putParams(params, pstmt);
            rs= pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 关闭资源
     */
    public void close(){
        try {
            if(rs!=null){
                rs.close();
            }
            if(pstmt !=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void putParams(Object[] params,PreparedStatement stmt) throws SQLException{
        if(params!=null){
            for(int i=1;i<=params.length;i++){
                stmt.setObject(i,params[i-1]);
            }
        }

    }
}