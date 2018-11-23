package com.antianbao.historydvd.MinDVD.login;

import org.junit.Test;

import java.util.Scanner;

public class Login {

	@Test
	@SuppressWarnings({ "resource" })
	public void loginManager() {
		System.out.println("********欢迎使用MiniDVD Mgr 1.0 管理系统********");
		System.out.println("------------------------------------------");
		System.out.println("-------------------1.登陆-------------------");
		System.out.println("-------------------2.退出-------------------");
		System.out.println("------------------------------------------");
		Scanner s = new Scanner(System.in); 
		System.out.println("请选择对应数字：");
		String login = s.nextLine(); 

		switch (login) {
		case "1":
			int i = 3;
			while(i>0) {
				String Gadmin = "admin";
				String Gpassword = "123";

				Scanner b = new Scanner(System.in); //使用Scanner类定义对象
				System.out.println("请输入您的用户名");
				String admin = b.nextLine();

				Scanner c = new Scanner(System.in); //使用Scanner类定义对象
				System.out.println("请输入您的密码");
				String password = c.nextLine();

				if(Gadmin.equals(admin) && Gpassword.equals(password)) {
					Choice choice = new Choice();
					choice.ChoiceManager();
					break;
				}else {
					i--;
					if(i!=0) {
						System.out.println("您输入的账号密码错误~还有" + i + "次机会~");
					}else {
						System.out.println("对不起，您无权登陆本系统~");
						break;
					}
				}
			}
			break;
		case "2":
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