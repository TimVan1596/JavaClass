package com.zhangmin.DVD;

public class DVDModel {
    private  String no;
    private  String name;
    private  String state;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DVDModel(String no, String name, String state) {
        this.no = no;
        this.name = name;
        this.state = state;
    }

    public DVDModel() {}
}
