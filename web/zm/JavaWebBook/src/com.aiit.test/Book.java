package com.aiit.model;

import java.util.Date;

public class Book {
private int bookid;
private BookType type;
private String bookname;
private String bookauthor;
private String bookprice;
private String booktime;
public int getBookid() {
	return bookid;
}
public void setBookid(int bookid) {
	this.bookid = bookid;
}
public BookType getType() {
	return type;
}
public void setType(BookType type) {
	this.type = type;
}
public String getBookname() {
	return bookname;
}
public void setBookname(String bookname) {
	this.bookname = bookname;
}
public String getBookauthor() {
	return bookauthor;
}
public void setBookauthor(String bookauthor) {
	this.bookauthor = bookauthor;
}
public String getBookprice() {
	return bookprice;
}
public void setBookprice(String bookprice) {
	this.bookprice = bookprice;
}
public String getBooktime() {
	return booktime;
}
public void setBooktime(String booktime) {
	this.booktime = booktime;
}
public Book() {
	super();
}
public Book(BookType type, String bookname, String bookauthor, String bookprice, String booktime) {
	super();
	this.type = type;
	this.bookname = bookname;
	this.bookauthor = bookauthor;
	this.bookprice = bookprice;
	this.booktime = booktime;
}
public Book(int bookid, BookType type, String bookname, String bookauthor, String bookprice, String booktime) {
	super();
	this.bookid = bookid;
	this.type = type;
	this.bookname = bookname;
	this.bookauthor = bookauthor;
	this.bookprice = bookprice;
	this.booktime = booktime;
}
}
