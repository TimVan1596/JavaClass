package com.antianbao.filedvd.user;
/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 *
 * 用户信息
 */
public class User {

    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public User() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
