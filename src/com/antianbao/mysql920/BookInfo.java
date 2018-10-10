package com.antianbao.mysql920;

public class BookInfo {
    int bookID;
    String BookTitle;
    int typeId;
    double price;
    String typeName;

    public BookInfo(){}

    public BookInfo(int bookID, String bookTitle, int typeId, double price, String typeName) {
        this.bookID = bookID;
        BookTitle = bookTitle;
        this.typeId = typeId;
        this.price = price;
        this.typeName = typeName;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        typeName = typeName;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookID=" + bookID +
                ", BookTitle='" + BookTitle + '\'' +
                ", typeId=" + typeId +
                ", price=" + price +
                '}';
    }
}
