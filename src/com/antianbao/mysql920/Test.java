package com.antianbao.mysql920;

import java.util.List;

/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * main
 */
public class Test {
    public static void main(String[] args){
        // 添加
        StudentDao stuDao = new StudentDao();
        // 新增账单
        Student stu = new Student();
        stu.setName("张三");
        stu.setAge(20);
        stu.setScore(60.5f);
        stu.setAddress("wuhu");
        ///*
        int rlt = stuDao.addStu(stu);
        if(rlt>0){
            System.out.println("新增成功");
        }
        //*/
        /*
        // 修改
        stu.setScore(80);
        int rlt = stuDao.updateStu("zhangsan",stu);
        if(rlt > 0){
            System.out.println("更新成功");
        }
        */
        /*
        // 删除
        int rlt = stuDao.deleteStu("zhangsan");
        if(rlt > 0){
            System.out.println("删除成功");
        }
        */
        /*
        // 查询
        List<Student> list = stuDao.queryStu(0,100);
        System.out.println("ID\tName\tAge\tScore\tAddress");
        for(Student ls:list){
            System.out.println(ls.getId() + '\t' +ls.getName() + '\t' +ls.getAge() + '\t' +ls.getScore() + '\t' +ls.getAddress());
        }
        */

    }
}
