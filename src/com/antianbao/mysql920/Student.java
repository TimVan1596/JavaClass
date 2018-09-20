package com.antianbao.mysql920;
/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * 学生类
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private float score;
    private String address;

    public Student(String name, int age, float score, String address) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.address = address;
    }

    public Student(int id, String name, int age, float score, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
