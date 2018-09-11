package com.antianbao.MinDVD.main;

public class DVD {
	private String no;
	private String name;
	private String state;
	
	public DVD(){}
	public DVD(String no, String name, String state){
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
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
