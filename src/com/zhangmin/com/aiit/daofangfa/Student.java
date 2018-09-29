package com.zhangmin.com.aiit.daofangfa;

public class Student {
    int stuID;
    String stuName;
    String stuAddress;
    float stuScore;

    public int getStuID() {
        return stuID;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public float getStuScore() {
        return stuScore;
    }

    public void setStuScore(float stuScore) {
        this.stuScore = stuScore;
    }

    public Student(int stuID,String stuName, String stuAddress, float stuScore) {
        this.stuID = stuID;
        this.stuName = stuName;
        this.stuAddress = stuAddress;
        this.stuScore = stuScore;
    }
    public Student(String stuName, String stuAddress, float stuScore) {
        this.stuName = stuName;
        this.stuAddress = stuAddress;
        this.stuScore = stuScore;
    }
    public Student(int stuID) {
        this.stuID = stuID;
    }
    @Override
    public String toString() {
        return stuID+"\t"+stuName+"\t"+stuAddress+"\t"+stuScore;
    }

}
