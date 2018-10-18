package com.zhangmin.com.aiit.daofangfa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    /*
    * Dao方法实现对学生的增删改查
    */
    //增
    private DBHelper dbHelper = new DBHelper();
    public void addStu(Student stu){
          String sql = "INSERT INTO tb_student(stuName,stuAddress,stuScore) values(?,?,?)";
          PreparedStatement pre = dbHelper.getPre(sql);
        try {
            pre.setString(1,stu.getStuName());
            pre.setString(2,stu.getStuAddress());
            pre.setFloat(3,stu.getStuScore());
            //执行处理
            int iRet = pre.executeUpdate();
            if(iRet>=0){
                System.out.println("添加成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbHelper.closeRescore();
        }

    }
    //删
   public void deleteStu(Student stu){
        String sql ="DELETE FROM tb_student WHERE stuID = "+stu.stuID;
        PreparedStatement pre = dbHelper.getPre(sql);
       int iRet = 0;
       try {
           iRet = pre.executeUpdate();
           if(iRet >=0){
               System.out.println("删除成功！");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           dbHelper.closeRescore();
       }

   }
    //改
    public void modlifyStu(Student stu){
        String sql ="UPDATE tb_student SET stuScore = stuScore+1 WHERE stuID="+stu.stuID;
        PreparedStatement pre = dbHelper.getPre(sql);
        try {
            int iRet = pre.executeUpdate();
            if (iRet >= 0) {
                System.out.println("修改成功！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbHelper.closeRescore();
        }

    }
    //查
    public List<Student> queryAllStudent(){
        List<Student> stuList= new ArrayList<Student>();
        String sql = "SELECT * FROM tb_student";
        Statement sta = dbHelper.getSta();
        try {
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("stuID");
                String name = rs.getString("stuName");
                String address = rs.getString("stuAddress");
                float score = rs.getFloat("stuScore");

                Student stu = new Student(ID, name, address, score);
                stuList.add(stu);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbHelper.closeRescore();
        }
          return  stuList;
    }
}
