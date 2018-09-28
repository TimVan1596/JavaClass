package com.zhangmin.com.aiit.daofangfa;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        //插入学生信息
//        Student stu = new Student("马尔哈","上海浦东",99);
//        studentDao.addStu(stu);
        List<Student> stuList= studentDao.queryAllStudent();
        for (Student stu:stuList) {
            System.out.println(stu);
        }
    }
}
