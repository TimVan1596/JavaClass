package com.antianbao.historydvd.serializedvd.dvd;

import java.io.Serializable;

/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 *
 * DVD信息
 */
public class Dvd implements Serializable {

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
