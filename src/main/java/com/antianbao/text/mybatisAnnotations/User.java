package com.antianbao.text.mybatisAnnotations;

public class User {
    private int id;
    private String email;
    private String password;

    public int getNo() {
        return id;
    }

    public void setNo(int id) {
        this.id = id;
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
