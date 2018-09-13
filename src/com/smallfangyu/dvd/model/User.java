package com.smallfangyu.dvd.model;

public class User{
	private String username;
	private String password;
	
	public User(){
		username="user";//用户名赋值
		password="123";//密码赋值
	}
    public String getUsername() {
    	return username;
      }
    public void setUsername(String username) {
    	this.username=username;
    }
    
    public String getPassword() {
    	return password;
      }
    public void setPassword(String password) {
    	this.password=password;
    }
	
}
