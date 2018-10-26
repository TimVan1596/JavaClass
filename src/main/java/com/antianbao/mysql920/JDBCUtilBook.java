package com.antianbao.mysql920;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtilBook {
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

    /**
     * 获得数据库连接对象Connection
     */
    private void openConnection() {
        //位置
        String DB_URL = "jdbc:mysql://" +
                "localhost:3306" +
                "/book" +
                "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8";
        //账号
        String USER = "root";
        //密码
        String PASS = "19980317";
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
        } catch (SQLException e) {
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
     * 将数据库中用户信息转为集合
     */
    public List<BookInfo> queryStu() {
        List<BookInfo> list = new ArrayList<BookInfo>();
        String sql = "select bookinfo.bookID,bookinfo.BookTitle,bookinfo.typeId,bookinfo.price,booktype.typename \n" +
                "from bookinfo,booktype \n" +
                "where bookinfo.typeId = bookType.typeID";
        PreparedStatement pstat = getPrepareStatement(sql);
        try {
            ResultSet rs = pstat.executeQuery();
            BookInfo bd = null;
            while (rs.next()) {
                bd = new BookInfo();
                bd.setBookID(rs.getInt("bookID"));
                bd.setBookTitle(rs.getString("BookTitle"));
                bd.setTypeId(rs.getInt("typeId"));
                bd.setPrice(rs.getDouble("price"));
                bd.setTypeName(rs.getString("booktype.typename"));
                list.add(bd);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
