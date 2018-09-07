package com.timvanx.biggerdvd.dvd;

/**
 * 账户类
 * @author TimVan
 * @date 2018年9月5日09:33:27
 * */
public class Account {
    private final String usrName = "admin";
    private final String usrPassword = "admin";

    public boolean login(String name , String password){
        boolean isLogin = false;

        if (name.equals(usrName) && password.equals(usrPassword)){
            System.out.println("登录成功！");
            isLogin = true;
        }
        else {
            System.out.println("账户或密码错误！");
        }

        return isLogin;
    }
}
