package com.zhangmin.DVD;

import java.util.Scanner;

public class Login {
    public void loginManager(){
        System.out.println("********欢迎使用MiniDVD Mgr 1.0 管理系统********");
        System.out.println("------------------------------------------");
        System.out.println("-------------------1.登陆-------------------");
        System.out.println("-------------------2.退出-------------------");
        System.out.println("------------------------------------------");
        Scanner num = new Scanner(System.in);
        System.out.println("请选择对应数字：");
        int login = num.nextInt();
        switch (login){
            case 1:
               int i = 3;
               while(i>0){
                   //定义用户名及密码
                   String user = "admin";
                   String password = "123";
                   //判断用户输入名及密码
                   System.out.println("请输入用户名：");
                   Scanner username = new Scanner(System.in);
                   String testname = username.nextLine();
                   System.out.println("请输入密码：");
                   Scanner userpassword  = new Scanner(System.in);
                   String testpassword = userpassword.nextLine();
                   if(user.equals(testname) && password.equals(testpassword)){
                        DVDMain dvdMain = new DVDMain();
                         dvdMain.choiceMenu();
                         break;
                   }else{
                       i--;
                       //判断用户还有多少次机会
                       if(i!=0){
                           System.out.println("您还有"+i+"次机会");
                       }else{
                           System.out.println("对不起，您本次登录系统失败，请下次再试！");
                           break;
                       }
                   }
               }
                          break;
            case 2:
                System.out.println("退出成功1");
                System.exit(-1);
                break;
        }
    }
}
