package com.antianbao.webtest;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.File;
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
 * 9月20日
 * 链接数据库
 */
public class JDBCUtilDvd {

    private static DruidDataSource druidDataSource = null;
    private PreparedStatement pstmt = null;
    private Connection conn = null;
    private ResultSet rs = null;

    //加载驱动
    static {
        //初始化数据库连接配置
        Properties properties = new Properties();

        try {
            properties.load(JDBCUtilDvd.class.getClassLoader()
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
        conn=getConn();
        String sql = "insert into dvd(name,state,image) values(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dvd.getName());
            pstmt.setInt(2, dvd.getState());
            pstmt.setString(3, dvd.getImage());
            rlt = pstmt.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 删除dvd操作
     */
    public int deleteDvd(int no) {
        int rlt = 0;
        conn=getConn();
        try {
            String sql = "DELETE FROM dvd where no = ?";
            pstmt = conn.prepareStatement(sql);
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
     * 删除图片操作
     */
    public int deleteImage(int no) {
        int rlt = 0;
        conn=getConn();
        List<Dvd> list = new ArrayList<Dvd>();
        String sql = "select image from recovery where no = "+no+" ";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Dvd bd = null;
            while (rs.next()) {
                bd = new Dvd();
                bd.setImage(rs.getString("image"));
                list.add(bd);
            }
            for (Dvd ls : list) {
                File file = new File("E:\\JAVA\\java_direction_class\\web\\atb\\javaWebDvd\\image\\"+ls.getImage());
                file.delete();
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 删除recovery操作
     */
    public int deleteRecovery(int no) {
        int rlt = 0;
        conn=getConn();
        try {
            String sql = "DELETE FROM recovery where no = ?";
            pstmt = conn.prepareStatement(sql);
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
     * 恢复数据
     */
    public int reductionAddDvd(int no) {
        int rlt = 0;
        conn=getConn();
        List<Dvd> list = new ArrayList<Dvd>();
        String sql = "select *from recovery where no = "+no+" ";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Dvd bd = null;
            while (rs.next()) {
                bd = new Dvd();
                bd.setNo(rs.getInt("no"));
                bd.setImage(rs.getString("image"));
                bd.setName(rs.getString("name"));
                bd.setState(rs.getInt("state"));
                bd.setBorrow(rs.getInt("borrow"));
                list.add(bd);
            }
            for (Dvd ls : list) {
                String sql1 = "insert into dvd(name,state,borrow,image) values(?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, ls.getName());
                pstmt.setInt(2, ls.getState());
                pstmt.setInt(3, ls.getBorrow());
                pstmt.setString(4, ls.getImage());
                rlt = pstmt.executeUpdate();
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 回收数据
     */
    public int recoveryAddDvd(int no) {
        int rlt = 0;
        conn=getConn();
        List<Dvd> list = new ArrayList<Dvd>();
        String sql = "select *from dvd where no = "+no+"";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Dvd bd = null;
            while (rs.next()) {
                bd = new Dvd();
                bd.setNo(rs.getInt("no"));
                bd.setImage(rs.getString("image"));
                bd.setName(rs.getString("name"));
                bd.setState(rs.getInt("state"));
                bd.setBorrow(rs.getInt("borrow"));
                list.add(bd);
            }
            for (Dvd ls : list) {
                String sql1 = "insert into recovery(name,state,borrow,image) values(?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, ls.getName());
                pstmt.setInt(2, ls.getState());
                pstmt.setInt(3, ls.getBorrow());
                pstmt.setString(4, ls.getImage());
                rlt = pstmt.executeUpdate();
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rlt;
    }

    /**
     * 修改dvd状态
     */
    public int updateState(int borrow,int no) {
        int rlt = 0;
        conn=getConn();
        try {
            String sql = "update dvd SET borrow = ? where no = ?";
            pstmt = conn.prepareStatement(sql);
            Object[] params = {borrow, no};
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
     * 修改recovery状态
     */
    public int recoveryState(int borrow,int no) {
        int rlt = 0;
        conn=getConn();
        try {
            String sql = "update recovery SET borrow = ? where no = ?";
            pstmt = conn.prepareStatement(sql);
            Object[] params = {borrow, no};
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
     * 编辑图书带图片
     */
    public int updateDvd(int no,String image, String name, int state,int borrow) {
        int rlt = 0;
        conn=getConn();
        try {
            String sql = "update dvd SET name = ?,state = ?,borrow = ?,image = ? where no = ?";
            pstmt = conn.prepareStatement(sql);
            Object[] params = {name,state,borrow,image,no};
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
     * 编辑图书无图片
     */
    public int updateDvd(int no, String name, int state,int borrow) {
        int rlt = 0;
        conn=getConn();
        try {
            String sql = "update dvd SET name = ?,state = ?,borrow = ? where no = ?";
            pstmt = conn.prepareStatement(sql);
            Object[] params = {name,state,borrow,no};
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
     * 将dvd数据库中用户信息转为集合
     */
    public List<Dvd> queryStu() {
        List<Dvd> list = new ArrayList<Dvd>();
        conn=getConn();
        String sql = "select *from dvd";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Dvd bd = null;
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
     * 将recovery数据库中用户信息转为集合
     */
    public List<Dvd> recovery() {
        List<Dvd> list = new ArrayList<Dvd>();
        conn=getConn();
        String sql = "select *from recovery";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Dvd bd = null;
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
     * 管理数据查找DVD
     */
    public List<Dvd> search(String search){
        List<Dvd> list = new ArrayList<>();
        conn=getConn();
        String sql = "SELECT *FROM dvd WHERE no like '%"+search+"%' or name like '%"+search+"%' or state-borrow like '%"+search+"%'";
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
     * 查询指定页（page这页）的记录
     * @param page
     * @return
     */
    public List<Dvd> find(int page){
        List<Dvd> list = new ArrayList<>();
        conn=getConn();
        String sql = "SELECT *FROM dvd LIMIT "+(page-1)* Dvd.PAGE_SIZE+","+ Dvd.PAGE_SIZE+"";
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
     * 查询指定页（page这页）的记录
     * @param page
     * @return
     */
    public List<Dvd> revokefind(int page){
        List<Dvd> list = new ArrayList<>();
        conn=getConn();
        String sql = "SELECT *FROM recovery LIMIT "+(page-1)* Dvd.PAGE_SIZE+","+ Dvd.PAGE_SIZE+"";
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
     * 总记录数
     * @return
     */
    public int findCount(){
        int count = 0;
        conn=getConn();
        String sql = "select count(*) from dvd";
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

    /**
     * 总记录数
     * @return
     */
    public int revokeFindCount(){
        int count = 0;
        conn=getConn();
        String sql = "select count(*) from recovery";
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

    /**
     * 查询指定页（page这页）的记录
     * @param page
     * @return
     */
    public List<Dvd> findPage(int page, String search){
        List<Dvd> list = new ArrayList<>();
        conn=getConn();
        String sql = "SELECT *FROM dvd " +
                "WHERE no like '%"+search+"%' or name like '%"+search+"%' or state-borrow like '%"+search+"%' " +
                "LIMIT "+(page-1)* Dvd.PAGE_SIZE+","+ Dvd.PAGE_SIZE+"";
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
     * 查询指定页（page这页）的记录
     * @param page
     * @return
     */
    public List<Dvd> revokeFindPage(int page, String search){
        List<Dvd> list = new ArrayList<>();
        conn=getConn();
        String sql = "SELECT *FROM recovery " +
                "WHERE no like '%"+search+"%' or name like '%"+search+"%' or state like '%"+search+"%' or borrow like '%"+search+"%' " +
                "LIMIT "+(page-1)* Dvd.PAGE_SIZE+","+ Dvd.PAGE_SIZE+"";
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
     * 总记录数
     * @return
     */
    public int revokeFindCountPage(String search){
        int count = 0;
        conn=getConn();
        String sql = "select count(*) from recovery " +
                "WHERE no like '%"+search+"%' or name like '%"+search+"%' or state like '%"+search+"%' or borrow like '%"+search+"%'";
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

    /**
     * 总记录数
     * @return
     */
    public int findCountPage(String search){
        int count = 0;
        conn=getConn();
        String sql = "select count(*) from dvd " +
                "WHERE no like '%"+search+"%' or name like '%"+search+"%' or state-borrow like '%"+search+"%'";
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
