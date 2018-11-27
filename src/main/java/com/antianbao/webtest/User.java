package com.antianbao.webtest;
/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 *
 * 用户信息
 */
public class User {

    private String username;
    private String userpassword;

    public User() {}

    public User(String username, String userpassword) {
        this.username = username;
        this.userpassword = userpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
