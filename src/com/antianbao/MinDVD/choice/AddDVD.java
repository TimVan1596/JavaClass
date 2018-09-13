package com.antianbao.MinDVD.choice;

import java.util.Scanner;

import com.antianbao.MinDVD.login.Choice;
import com.antianbao.MinDVD.main.DVD;

public class AddDVD {
	public void AddDVDManager(DVD[] dvds) {
		// TODO Auto-generated method stub
		System.out.println("请输入要添加DVD的编号：");
		Scanner s = new Scanner(System.in); 
		String no = s.nextLine();

		System.out.println("请输入要添加DVD的名称：");
		Scanner s1 = new Scanner(System.in); 
		String name = s1.nextLine();

		Choice c = new Choice();
		//c.Choice();
		//将info转为dvd存储
		DVD dvd = new DVD(no, name, "可以借");

		// 将归还的DVD添加到存放所有DVD的数组中
		for(int i = 0; i < dvds.length; i++) {
			if(dvds[i] == null) {
				dvds[i] = dvd;
				// 添加成功后，要更新数组数据源
				c.setDVDs(dvds);
				break;
			}
		}

		System.out.println("MiniDVD Mgr 1.0 管理系统---->显示当前所有DVD信息");
		System.out.println("编号\t名称\t\t状态");
		for(int i = 0; i < dvds.length; i++) {
			if(dvds[i] != null) {
				System.out.println(dvds[i].getNo() + "\t" + dvds[i].getName() + "\t" + dvds[i].getState());
			}
		}

		while(true) {
			System.out.println("是否继续？ y/n");
			Scanner s11 = new Scanner(System.in); 
			String yn = s11.nextLine();
			switch(yn) {
			case "y":
				AddDVDManager(dvds);
				break;
			case "n":
				Scanner s12 = new Scanner(System.in); 
				System.out.println("请输入数字0返回：");
				String Choice = s12.nextLine();
				if (Choice.equals("0")) {
					c.ChoiceManager();
				}else {
					System.out.println("输入数字错误，请重新输入！");
					AddDVDManager(dvds);
				}
				break;
			default:
				System.out.println("请输入y或n~");
				break;
			}
		}
	}

}