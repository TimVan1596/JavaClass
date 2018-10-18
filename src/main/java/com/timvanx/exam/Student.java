package com.timvanx.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student implements Comparable<Student> {

    private String name;
    private double score;
    private char level;

    public Student(String name, double score) {
        this.name = name;
        this.score = score;

        if (score >= 90) {
            this.level = 'A';
        } else if (score >= 60) {
            this.level = 'B';
        } else if (score < 60) {
            this.level = 'C';
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Lily", 90.0));
        students.add(new Student("Lucy", 60.0));
        students.add(new Student("Lilei", 30.0));
        students.add(new Student("HanMeimei", 70.0));

        //查询等级B的学生(并生成新的集合)
        System.out.println("查询等级B的学生");
        ArrayList<Student> B_Stu = getB(students);

        //排序接口排序B，成绩升序
        System.out.println("\n\n排序接口排序B，成绩升序");
        Collections.sort(B_Stu);
        for (Student stu : B_Stu) {

            System.out.println(stu);
        }

    }

    //查询等级B的学生(并生成新的集合)
    public static ArrayList<Student> getB(List<Student> students) {
        //返回B等级的学生集合
        ArrayList<Student> B_Stu = new ArrayList<>();

        for (Student stu : students) {
            if (stu.level == 'B') {
                System.out.println(stu);
                B_Stu.add(stu);
            }
        }

        return B_Stu;
    }

    //排序接口排序B，成绩升序
    @Override
    public int compareTo(Student student) {

        return (int) (score - student.score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public char getLevel() {
        return level;
    }

    public void setLevel(char level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Student{" +
                "姓名='" + name + '\'' +
                ", 成绩=" + score +
                ", 等级=" + level +
                '}';
    }


}
