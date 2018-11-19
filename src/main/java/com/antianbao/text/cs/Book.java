package com.antianbao.text.cs;

public class Book {
    private int id;
    private String name;
    private String author;
    private int price;
    private String date;
    private String type;

    public Book(){}
    public Book(int id,String name,String author,int price,String date,String type){
        this.id=id;
        this.name=name;
        this.author=author;
        this.price=price;
        this.date=date;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
