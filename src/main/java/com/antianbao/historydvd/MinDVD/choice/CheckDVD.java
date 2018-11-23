package com.antianbao.historydvd.MinDVD.choice;

import java.util.Scanner;
import com.antianbao.historydvd.MinDVD.login.Choice;
import com.antianbao.historydvd.MinDVD.main.DVD;

public class CheckDVD{
	// TODO Auto-generated constructor stub
	@SuppressWarnings("resource")
	public void CheckDVDManager(DVD[] dvds) {
		
		System.out.println("MiniDVD Mgr 1.0 管理系统---->查询DVD信息");
		System.out.println("----------------------------------");
		System.out.println("-------------1.按编号查询------------");
		System.out.println("-------------2.按名称查询------------");
		System.out.println("---------------0.返回--------------");
		System.out.println("----------------------------------");
		System.out.println("请选择查询的方式：");

		Scanner s = new Scanner(System.in); 
		int Choice = s.nextInt();
		
		if (Choice == 1) {
			System.out.println("请输入要查询的DVD编号：");
			Scanner s1 = new Scanner(System.in); 
			String no = s1.nextLine();
			
			// 标记查找的DVD是否存在（默认不存在）
			boolean isExist = false;
			
			for(int i = 0; i < dvds.length;i++) {
				// 判断是否存在编号为no的DVD
				if( dvds[i] != null && no.equals(dvds[i].getNo()) ) {
					// 存在，更改标记为true
					isExist = true;
					System.out.println("编号\t名称\t\t状态");
					System.out.println(dvds[i].getNo() + "\t" + dvds[i].getName() + "\t" + dvds[i].getState());
					break;
				}
			}
			// 不存在
			if(!isExist) {
				System.out.println("所输入的DVD编号不正确！请重试！");
				CheckDVDManager(dvds);
			}
			while(true) {
				System.out.println("是否继续？ y/n");
				Scanner s11 = new Scanner(System.in); 
				String yn = s11.nextLine();
				switch(yn) {
				case "y":
					CheckDVDManager(dvds);
					break;
				case "n":
					System.out.println("请输入数字0返回：");
					Scanner s2 = new Scanner(System.in); 
					String Choice1 = s2.nextLine();
					if (Choice1.equals("0")) {
						Choice c = new Choice();
						c.ChoiceManager();
					}else {
						System.out.println("输入数字错误，请重新输入！");
						CheckDVDManager(dvds);
					}
					break;
				default:
					System.out.println("请输入y或n~");
					break;
				}
			}
		}else if(Choice == 2){
			Scanner s1 = new Scanner(System.in); 
			System.out.println("请输入要查询的DVD名称：");
			String name = s1.nextLine();
			
			boolean isExist = false;
			
			for(int i = 0; i < dvds.length;i++) {
				if( dvds[i] != null && name.equals(dvds[i].getName()) ) {
					isExist = true;
					System.out.println("编号\t名称\t\t状态");
					System.out.println(dvds[i].getNo() + "\t" + dvds[i].getName() + "\t" + dvds[i].getState());
					//break;
				}
			}
			if(!isExist) {
				System.out.println("此DVD不存在！");
				CheckDVDManager(dvds);
			}
			while(true) {
				System.out.println("是否继续？ y/n");
				Scanner s11 = new Scanner(System.in); 
				String yn = s11.nextLine();
				switch(yn) {
				case "y":
					CheckDVDManager(dvds);
					break;
				case "n":
					System.out.println("请输入数字0返回：");
					Scanner s2 = new Scanner(System.in); 
					String Choice1 = s2.nextLine();
					if (Choice1.equals("0")) {
						Choice c = new Choice();
						c.ChoiceManager();
					}else {
						System.out.println("输入数字错误，请重新输入！");
						CheckDVDManager(dvds);
					}
					break;
				default:
					System.out.println("请输入y或n~");
					break;
				}
			}
		}else if(Choice == 0){
			Choice Ldvd = new Choice();
			Ldvd.ChoiceManager();
		}else {
			System.out.println("请输入正确的选择~");
			CheckDVDManager(dvds);
		}
	}
}