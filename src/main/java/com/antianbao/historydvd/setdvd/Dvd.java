package com.antianbao.historydvd.setdvd;
/**
 * @author 安天宝
 * JAVA一班
 * 9月13日
 */
public class Dvd {

	private int no;
	private String name;
	private String state;

	public Dvd(int no, String name, String state){
		this.name = name;
		this.no = no;
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return  no + "\t" + name + "\t\t" + state;
	}
}
