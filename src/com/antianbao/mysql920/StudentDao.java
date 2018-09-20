package com.antianbao.mysql920;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * 操作
 */
public class StudentDao {
    private DBHelper dbHelper = new DBHelper();
    public int addStu(Student stu){
        int rlt = 0;
        String sql = "insert into student(name,age,score,address) values(?,?,?,?)";
        PreparedStatement pstmt = dbHelper.getPrepareStatement(sql);
        try {
            pstmt.setString(1,stu.getName());
            pstmt.setInt(2,stu.getAge());
            pstmt.setFloat(3,stu.getScore());
            pstmt.setString(4,stu.getAddress());
            rlt = pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    public int deleteStu(String name) {
        int rlt = 0;
        try {
            String sql = "DELETE FROM student where name = ?";
            PreparedStatement pstmt = dbHelper.getPrepareStatement(sql);
            pstmt.setObject(1, name);
            rlt = pstmt.executeUpdate();
            dbHelper.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    public int updateStu(String name,Student bd) {
        int rlt = 0;
        try {
            String sql = "update student SET name = ?,age = ?,score = ?,address = ?  where name = ?";
            PreparedStatement pstat = dbHelper.getPrepareStatement(sql);
            Object[] params = {bd.getName(),bd.getAge(),bd.getScore(),bd.getAddress(),name};
            for (int i = 1; i <= params.length; i++) {
                pstat.setObject(i, params[i - 1]);
            }
            rlt = pstat.executeUpdate();
            dbHelper.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rlt;
    }

    public List<Student> queryStu(int startScore, int endScore){
        List<Student> list = new ArrayList<Student>();
        String sql = "select *from student where score>=? and score<=?";
        PreparedStatement pstat = dbHelper.getPrepareStatement(sql);
        try {
            pstat.setObject(1, startScore);
            pstat.setObject(2, endScore);
            ResultSet rs = pstat.executeQuery();
            Student bd = null;
            while (rs.next()) {
                bd = new Student();
                bd.setId(rs.getInt("id"));
                bd.setName(rs.getString("name"));
                bd.setAge(rs.getInt("age"));
                bd.setScore(rs.getFloat("score"));
                bd.setAddress(rs.getString("address"));
                list.add(bd);
            }
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
