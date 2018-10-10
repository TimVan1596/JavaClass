package com.antianbao.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class Student {
    String name;
    int score;

    public Student() {}

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return  name + "\t" + score;
    }
}

public class ceshi {
    static ArrayList<Student> stus = new ArrayList<>();
    //数据的初始化
    static {
        Student stu1 = new Student("Lily", 90);
        Student stu2 = new Student("Lucy", 60);
        Student stu3 = new Student("Lilei", 30);
        Student stu4 = new Student("HanMeimei", 70);
        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);
        stus.add(stu4);
    }
    public static void main(String[] args) {
        Collections.sort(stus, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getScore()-o2.getScore();
            }
        });
        //循环
        for (Student stu : stus) {
            //判断等级B
            if(stu.getScore() >= 60 && stu.getScore() < 90){
                //输出数据
                System.out.println(stu);
            }
        }
    }
}
