package com.zhangmin.com.aiit.myBatis;

public class Student {
    private int stuId;
    private String stuName;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Student() {
    }

    public Student(int stuId, String stuName) {
        this.stuId = stuId;
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "学号：" + stuId +
                ", 姓名：'" + stuName + '\'' +
                '}';
    }
}
