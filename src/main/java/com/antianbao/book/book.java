package com.antianbao.book;

public class book {

    public static final int PAGE_SIZE = 10; //每页显示的数量

    private String bookid;
    private String bookname;
    private String Double;
    private String datetime;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getDouble() {
        return Double;
    }

    public void setDouble(String aDouble) {
        Double = aDouble;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
