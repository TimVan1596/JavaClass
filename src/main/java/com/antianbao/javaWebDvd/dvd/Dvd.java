package com.antianbao.javaWebDvd.dvd;
/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 * DVD信息
 */
public class Dvd {
    public static final int PAGE_SIZE = 8; //每页显示的数量

	private int no;
    private String image;
	private String name;
	private int state;
	private int borrow;

	public Dvd(){}
	public Dvd(String name, int state){
		this.name = name;
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

    @Override
    public String toString() {
        return "Dvd{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", borrow=" + borrow +
                '}';
    }
}