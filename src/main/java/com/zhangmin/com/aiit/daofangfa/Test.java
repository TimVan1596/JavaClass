package com.zhangmin.com.aiit.daofangfa;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        //插入学生信息
//        BookType stu = new BookType("马尔哈","上海浦东",99);
//        studentDao.addStu(stu);
        //查看学生信息
//        List<BookType> stuList= studentDao.queryAllStudent();
//        for (BookType stu:stuList) {
//            System.out.println(stu);
//        }
        //删除学生信息
//        BookType stu =new BookType(5);
//          studentDao.deleteStu(stu);
        //修改学生信息
        Student stu = new Student(6);
          studentDao.modlifyStu(stu);
    }
}
