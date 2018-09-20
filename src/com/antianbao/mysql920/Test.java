package com.antianbao.mysql920;
/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * main
 */
public class Test {
    public static void main(String[] args){
        Student stu = new Student("atb",35,99,"aaaa");
        StudentDao stuDao = new StudentDao();
        stuDao.saveStu(stu);
    }
}
