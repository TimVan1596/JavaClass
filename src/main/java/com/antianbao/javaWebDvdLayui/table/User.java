package com.antianbao.javaWebDvdLayui.table;
/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 *
 * 用户信息
 */
public class User {

    private String email;
    private String password;

    public User() {}
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
