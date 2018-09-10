package com.timvanx.biggerdvd;

import com.timvanx.biggerdvd.dvd.Account;
import com.timvanx.biggerdvd.dvd.Menu;
import com.timvanx.biggerdvd.util.Constants;

import java.util.Scanner;

/**
 * BiggerDVD 管理系统 ，比mini更Bigger
 *
 * @author TimVan
 * @date 2018年9月5日08:41:12
 */
public class BiggerDVD {
    /**
     * MAX_LOGIN_TIMES = 登录最大尝试次数
     * account = 读取与录入账户
     */
    private static final int MAX_LOGIN_TIMES = 3;
    private static Account account;

    static {
        account = new Account();
    }

    /**
     * 登录选项
     */
    private boolean loginOption(){
        int loginCNT = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isProgramContinue = true;


        for (int i = 0; i < MAX_LOGIN_TIMES; i++) {
            System.out.println("请输入用户名");
            String userName = scanner.nextLine();
            System.out.println("请输入密码");
            String userPassword = scanner.nextLine();
            if (Account.login(userName, userPassword)) {
                System.out.println("即将进入系统！");
                Menu menu = new Menu();
                menu.init(userName);
                break;
            }
            loginCNT++;
        }
        if (loginCNT == MAX_LOGIN_TIMES) {
            System.out.println("尝试次数过多，终止程序！");
            isProgramContinue = false;
        }
        return isProgramContinue;
    }

    /**
     * 注册选项
     */
    private boolean registerOption(){
        Scanner scanner = new Scanner(System.in);
        boolean isProgramContinue = true;

        System.out.println("请输入新的用户名");
        String userName = scanner.nextLine();
        System.out.println("请输入密码");
        String userPassword = scanner.nextLine();
        if (Account.register(userName, userPassword)) {
            System.out.println("注册成功！");
        }else {
            System.out.println("登录失败！请检查用户名或密码是否输入错误");
        }

        return isProgramContinue;
    }

    /**
     * 系统主界面函数
     * @return isEndProgram 退出UI界面循环标识
     */
    private boolean systemUI() {
        //退出UI界面循环标识
        boolean isProgramContinue = true;
        int inputNum = 0;

        //展示输入选项内容
        for (SystemUIOption option : SystemUIOption.values()) {
            System.out.println(option.getKey() + "、" + option.getValue());
        }
        System.out.print("请输入对应数字：");

        inputNum = Constants.scanfInt();

        switch (inputNum) {
            //登录
            case 1:
                isProgramContinue = loginOption();
                break;
            //注册
            case 2:
                isProgramContinue = registerOption();
                break;
            //退出
            case 0:
                isProgramContinue = false;
                break;
            //数字不在范围内
            default:
                System.out.println("数字不在范围内，请重新输入");
                break;
        }

        return isProgramContinue;
    }

    public static void main(String[] args) {

        System.out.println("********** 欢迎来到BiggerDVD 管理系统 **************");
        System.out.println("**版本号 " + Constants.VERSION);
        while (new BiggerDVD().systemUI()) {

        }
        System.out.println("** 已经安全退出系统，您可关闭此窗口 **");
    }
}

/**
 * 界面选项中数字-内容
 * */
enum SystemUIOption {
    Login(1, "登录"), Register(2, "注册"), Exit(0, "退出程序");
    private final int key;
    private final String value;

    SystemUIOption(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}



