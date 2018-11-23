package com.antianbao.historydvd.MinDVD.choice;

import java.util.Scanner;

import com.antianbao.historydvd.MinDVD.login.Choice;
import com.antianbao.historydvd.MinDVD.main.DVD;

public class DisplayDVD{
	// TODO Auto-generated constructor stub
	@SuppressWarnings("resource")
	public void DisplayDVDManager(DVD[] dvds) {
		System.out.println("MiniDVD Mgr 1.0 管理系统---->显示当前所有DVD信息");
		System.out.println("编号\t名称\t\t状态");
		
		for(int i = 0; i < dvds.length; i++) {
			if(dvds[i] != null) {
				System.out.println(dvds[i].getNo() + "\t" + dvds[i].getName() + "\t" + dvds[i].getState());
			}
		}

		System.out.println("请输入数字0返回：");
		Scanner s = new Scanner(System.in); 
		int Choice = s.nextInt();
		if (Choice == 0) {
			Choice c = new Choice();
			c.ChoiceManager();
		}else {
			System.out.println("输入数字错误，请重新输入！");
			DisplayDVDManager(dvds);
		}
	}
}