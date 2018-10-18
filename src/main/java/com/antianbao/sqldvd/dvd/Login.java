package com.antianbao.sqldvd.dvd;

import com.antianbao.sqldvd.user.JDBCUtilUser;
import com.antianbao.sqldvd.user.User;

import java.util.Scanner;

/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 * <p>
 * 首页
 */
public class Login {
    @SuppressWarnings({"resource"})
    public void loginManager() {
        System.out.println("********欢迎使用MiniDVD Mgr 5.0 管理系统********");
        System.out.println("------------------------------------------");
        System.out.println("-------------------1.注册-------------------");
        System.out.println("-------------------2.登陆-------------------");
        System.out.println("-----------------3.修改密码-----------------");
        System.out.println("-----------------4.找回密码-----------------");
        System.out.println("-------------------5.退出-------------------");
        System.out.println("------------------------------------------");
        Scanner s = new Scanner(System.in);
        System.out.println("请选择对应数字：");
        String login = s.nextLine();
        switch (login) {
            case "1":
                while (true) {
                    System.out.println("------欢迎来到注册界面------");
                    System.out.println("请输入用户名:");
                    String useName = s.nextLine();
                    System.out.println("请输入密码:");
                    String pwd = s.nextLine();
                    System.out.println("请输入手机号:");
                    String phone = s.nextLine();
                    User user = new User(useName, pwd, phone);
                    JDBCUtilUser jdbcUtil = new JDBCUtilUser();
                    int jd = jdbcUtil.addStu(user);
                    if (jd > 0) {
                        System.out.println("注册成功！");
                        loginManager();
                    } else {
                        System.out.println("用户已经存在");
                        System.out.println("-- 是否继续？ y/n --");
                        String yn = s.nextLine();
                        switch (yn) {
                            case "y":
                                break;
                            case "n":
                                System.out.println("-----1.返回至菜单-----");
                                System.out.println("-----2.退出此程序-----");
                                int choice = s.nextInt();
                                try {
                                    if (choice == 1) {
                                        loginManager();
                                    } else if (choice == 2) {
                                        System.exit(-1);
                                    }
                                } catch (Exception e) {
                                    System.out.println("请输入正确的选择！");
                                }
                                break;
                            default:
                                System.out.println("请输入y或n~");
                                break;
                        }
                    }
                }
            case "2":
                int i = 3;
                while (i > 0) {
                    System.out.println("------欢迎来到登陆界面------");
                    System.out.println("请输入用户名:");
                    String Name = s.nextLine();
                    System.out.println("请输入密码:");
                    String Pwd = s.nextLine();
                    JDBCUtilUser jdbcUtil = new JDBCUtilUser();
                    int jd = jdbcUtil.isReally(Name, Pwd);
                    if (jd > 0) {
                        System.out.println("登录成功！");
                        Choice choice = new Choice();
                        choice.choiceManager();
                    } else {
                        i--;
                        if (i != 0) {
                            System.out.println("您输入的账号或密码错误~还有" + i + "次机会~");
                        } else {
                            System.out.println("对不起，您无权登陆本系统~");
                            break;
                        }
                    }
                }
                break;
            case "3":
                int j = 3;
                while (j > 0) {
                    System.out.println("------修改密码------");
                    System.out.println("请输入用户名:");
                    String Name = s.nextLine();
                    System.out.println("请输入旧密码:");
                    String Pwd = s.nextLine();
                    JDBCUtilUser jdbcUtil = new JDBCUtilUser();
                    int jd = jdbcUtil.isReally(Name, Pwd);
                    if (jd > 0) {
                        System.out.println("请输入新密码：");
                        String newPwd = s.nextLine();
                        int jdc = jdbcUtil.updateStu(Name, newPwd);
                        if (jdc > 0) {
                            System.out.println("修改成功！");
                            loginManager();
                        }
                    } else {
                        j--;
                        if (j != 0) {
                            System.out.println("您输入的账号或密码有误！还有" + j + "次机会！");
                        } else {
                            System.out.println("对不起，您无权进行本操作！");
                            loginManager();
                        }
                    }
                }
                break;
            case "4":
                int k = 3;
                while (k > 0) {
                    System.out.println("------找回密码------");
                    System.out.println("请输入用户名:");
                    String Name = s.nextLine();
                    System.out.println("请输入手机号:");
                    String Phone = s.nextLine();
                    JDBCUtilUser jdbcUtil = new JDBCUtilUser();
                    int jd = jdbcUtil.isPhone(Name, Phone);
                    if (jd > 0) {
                        System.out.println("请输入新密码：");
                        String newPwd = s.nextLine();
                        int jdc = jdbcUtil.updateStu(Name, newPwd);
                        if (jdc > 0) {
                            System.out.println("修改成功！");
                            loginManager();
                        }
                    } else {
                        k--;
                        if (k != 0) {
                            System.out.println("您输入的账号或手机号有误！还有" + k + "次机会！");
                        } else {
                            System.out.println("对不起，您无权进行本操作！");
                            loginManager();
                        }
                    }
                }
                break;
            case "5":
                System.out.println("退出成功！");
                System.exit(-1);
                break;
            default:
                System.out.println("输入有误，请重新输入！");
                loginManager();
                break;
        }
    }

}