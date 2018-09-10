package com.timvanx.biggerdvd.dvd;

import java.util.ArrayList;

/**
 * 账户类
 * @author TimVan
 * @date 2018年9月5日09:33:27
 * */
public class Account {
    private final String usrName = "root";
    private final String usrPassword = "root";

    /**
     * accounts 保存所有的账户，静态
     */
    static ArrayList<Account> accountArrayList;

    public Account() {
        accountArrayList = new ArrayList<>();
    }

    /**
     * 登录
     */
    public static boolean login(String name, String password) {
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

    /**
     * 注册
     */
    public static boolean register(String name, String password) {

    }
}
