package com.zhangmin.com.aiit.myBatis;

import java.util.List;

public interface IStu {
    List<Student> getStuList();

    void insertStu(Student student);

    void updateStu(Student student);

    void deleteStu(int stuId);

    Student getStu(int id);
}
