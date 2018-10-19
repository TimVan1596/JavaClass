package com.antianbao.scImg;

import java.sql.Blob;

public class BookInfo {
    private  String name;
    private Blob img;

    public BookInfo(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }
}
