package com.antianbao.mysql920;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * 操作
 */
public class StudentDao {
    private DBHelper dbHelper = new DBHelper();

    public void saveStu(Student stu){
        String sql = "insert into student(name,age,score,address) values(?,?,?,?)";
        PreparedStatement pstmt = dbHelper.getPrepareStatement(sql);
        try {
            pstmt.setString(1,stu.getName());
            pstmt.setInt(2,stu.getAge());
            pstmt.setFloat(3,stu.getScore());
            pstmt.setString(4,stu.getAddress());
            pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
