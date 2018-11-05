package com.aiit.model;

public class BookType {
private int typeid;
private String typename;
public BookType() {
	super();
}
public BookType(int typeid) {
	super();
	this.typeid = typeid;
}
public BookType(String typename) {
	super();
	this.typename = typename;
}
public BookType(int typeid, String typename) {
	super();
	this.typeid = typeid;
	this.typename = typename;
}
public int getTypeid() {
	return typeid;
}
public void setTypeid(int typeid) {
	this.typeid = typeid;
}
public String getTypename() {
	return typename;
}
public void setTypename(String typename) {
	this.typename = typename;
}
}
