package com.smallfangyu.jdbcdvd.data;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;


public class DbUtil {
    /**
     * 设置驱动、url、用户名、密码
     * 通过db.properties配置
     */
    private static  String JDBC_DRIVER ;
    private static  String DB_URL;
    private static  String USER ;
    private static  String PASS ;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    /**
     * 初始化JDBC-MySQL连接
     */
   public static void init(){
    //初始化数据库连接配置
       Properties properties=new Properties();
       InputStream in=DbUtil.class.getClassLoader().getResourceAsStream("db.properties");

       //读取db.properties
       try {
           properties.load(in);
       } catch (IOException e) {
           e.printStackTrace();
       }
       //赋值
       JDBC_DRIVER=properties.getProperty("JDBC_DRIVER");
       DB_URL=properties.getProperty("DB_URL");
       USER=properties.getProperty("USER");
       PASS=properties.getProperty("PASS");

       //注册JDBC驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
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
             if (conn == null || conn.isClosed()) {
                 conn = DriverManager.getConnection(DB_URL,USER,PASS);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
          return conn;
     }


     /**
     * 封装SELECT语句
     */
    public  List<List<String>> select(ArrayList<String> tableSelect,String tableFrom,String tableWhere,String tableOrder,String tableLimit){

        getConn();

        List<List<String>> list=new ArrayList<>();

       StringBuilder stringBuilder=new StringBuilder();
       stringBuilder.append(" SELECT ");
        stringBuilder.append(arrayListToPreparedStm(tableSelect));
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableFrom);
        if(tableWhere!=null){
            stringBuilder.append(" WHERE ");
            stringBuilder.append(tableWhere);
        }
        if(tableOrder!=null){
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(tableOrder);
        }
        if(tableLimit!=null){
            stringBuilder.append(" LIMIT ");
            stringBuilder.append(tableLimit);
        }
        String sql=stringBuilder.toString();
        try{
         stmt = conn.prepareStatement(sql) ;
         rs=stmt.executeQuery();
         while(rs.next()){
             ArrayList<String> row = new ArrayList<>();
             for(String selectData:tableSelect) {
                 row.add(rs.getString(selectData));
             }
             list.add(row);
         }
         close();
        }catch(SQLException e){
            e.printStackTrace();

        }

        return list;
    }

    /**
     * 封装UPDATE语句
     * @param tableName
     * @param updateSet
     * @param updateWhere
     * @return
     */
    public int update(String tableName,String updateSet,String updateWhere){
        getConn();
        int result=0;
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(" UPDATE ");
        stringBuilder.append(tableName);
        stringBuilder.append(" SET ");
        stringBuilder.append(updateSet);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(updateWhere);

        String sql=stringBuilder.toString();
System.out.println(sql);
        try {
            stmt=conn.prepareStatement(sql);
            result=stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 封装插入语句INSERT
     * @param insertInto
     * @param insertValues
     * @return
     */
    public int insert(String tableName, String insertInto, String insertValues){
        getConn();
        int result=0;

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(" INSERT ");
        stringBuilder.append(" INTO ");
        stringBuilder.append(tableName);
        if(insertInto!=null){
            stringBuilder.append("("+insertInto+")");
        }
        stringBuilder.append(" VALUES");
        stringBuilder.append("("+insertValues+")");

        String sql=stringBuilder.toString();

        try {
            stmt=conn.prepareStatement(sql);
            result=stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 封装DELETE语句
     * @param tableName
     * @param deleteWhere
     * @return
     */
     public int delete(String tableName,String deleteWhere){
        int res=0;
        getConn();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(" DELETE FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(deleteWhere);

        String sql=stringBuilder.toString();
         try{
             stmt = conn.prepareStatement(sql);
             res=stmt.executeUpdate();
             close();
         }catch(SQLException e){
             e.printStackTrace();
         }

        return res;
      }


    /**
     * 将ArrayList转换成 StringBuilder 格式
     * @param arrayList 原始ArrayList，保存各字段
     * @return StringBuilder 返回输出结果
     */
    private static StringBuilder arrayListToPreparedStm(ArrayList<String> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!arrayList.isEmpty()) {
            boolean isFirst = true;
            for (int i = 0; i < arrayList.size(); i++) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stringBuilder.append(" , ");
                }
                stringBuilder.append(arrayList.get(i));
            }
        }
        return stringBuilder;
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


}
