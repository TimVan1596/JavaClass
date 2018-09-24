package com.smallfangyu.jdbcdvd.data;

import java.sql.*;


public class DbUtil {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dvdsystem?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8";
    static final String USER = "root";
    static final String PASS = "";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    static {
        //注册JDBC驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
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
             if (conn == null || conn.isClosed()) {
                 conn = DriverManager.getConnection(DB_URL,USER,PASS);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
          return conn;
     }

    /**
     *更新数据库
     * @return
     */
    public int executeUpdate(String sql,Object[] params){
            int rlt=-1;
            getConn();
            try {
                stmt = conn.prepareStatement(sql);
                putParams(params,stmt);
                rlt=stmt.executeUpdate();

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
        getConn();
        try {
            stmt=conn.prepareStatement(sql);
            putParams(params,stmt);
            rs=stmt.executeQuery();
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
                  if(stmt!=null){
                  stmt.close();
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
