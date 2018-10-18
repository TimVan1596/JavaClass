package com.antianbao.sqldvd.user;
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
    private String phone;
    public User(String name, String password,String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
