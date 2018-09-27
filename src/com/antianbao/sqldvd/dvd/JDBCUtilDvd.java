package com.antianbao.sqldvd.dvd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * 链接数据库
 */
public class JDBCUtilDvd {
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
                "/sqldvd" +
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
     * 获得Statement
     */
    public Statement getStatment() {
        openConnection();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
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
     * 添加图书
     */
    public int addDvd(Dvd dvd) {
        int rlt = 0;
        List<Dvd> list = queryStu();
        for (Dvd ls : list) {
            if (ls.getName().equals(dvd.getName())) {
                return rlt;
            }
        }
        String sql = "insert into dvd(name,state) values(?,?)";
        PreparedStatement pstmt = getPrepareStatement(sql);
        try {
            pstmt.setString(1, dvd.getName());
            pstmt.setString(2, dvd.getState());
            rlt = pstmt.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 删除操作
     */
    public int deleteDvd(int no) {
        int rlt = 0;
        try {
            String sql = "DELETE FROM dvd where no = ?";
            PreparedStatement pstmt = getPrepareStatement(sql);
            pstmt.setObject(1, no);
            rlt = pstmt.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 修改状态
     */
    public int updateState(String state,int no) {
        int rlt = 0;
        try {
            String sql = "update dvd SET state = ? where no = ?";
            PreparedStatement pstat = getPrepareStatement(sql);
            Object[] params = {state, no};
            for (int i = 1; i <= params.length; i++) {
                pstat.setObject(i, params[i - 1]);
            }
            rlt = pstat.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 修改图书
     */
    public int updateDvd(int no, String name) {
        int rlt = 0;
        try {
            String sql = "update dvd SET name = ? where no = ?";
            PreparedStatement pstat = getPrepareStatement(sql);
            Object[] params = {name, no};
            for (int i = 1; i <= params.length; i++) {
                pstat.setObject(i, params[i - 1]);
            }
            rlt = pstat.executeUpdate();
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
    public List<Dvd> queryStu() {
        List<Dvd> list = new ArrayList<Dvd>();
        String sql = "select *from dvd";
        PreparedStatement pstat = getPrepareStatement(sql);
        try {
            ResultSet rs = pstat.executeQuery();
            Dvd bd = null;
            while (rs.next()) {
                bd = new Dvd();
                bd.setNo(rs.getInt("no"));
                bd.setName(rs.getString("name"));
                bd.setState(rs.getString("state"));
                list.add(bd);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
