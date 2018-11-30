package com.antianbao.javaWebDvdLayui.table;
/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 * DVD信息
 */
public class Dvd {

	private int no;
	private String name;
	private int state;
	private int borrow;
	private String image;

	public Dvd(){}

	public Dvd(String name, int state, String image){
		this.name = name;
		this.state = state;
		this.image = image;
	}

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBorrow() {
        return borrow;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
