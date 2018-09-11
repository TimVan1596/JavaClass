package com.antianbao.MinDVD.choice;

import java.util.Scanner;

import com.antianbao.MinDVD.login.Choice;
import com.antianbao.MinDVD.main.DVD;

public class LendDVD {
	
	@SuppressWarnings("resource")
	public void LendDVDManager(DVD[] dvds) {
		// TODO Auto-generated method stub
		
		Choice c = new Choice();
		
		System.out.println("MiniDVD Mgr 1.0 管理系统---->借出DVD信息");
		System.out.println("请输入要借出的DVD编号：");
		
		Scanner s = new Scanner(System.in); 
		String no = s.nextLine();

		// 标记是否成功借到DVD（默认DVD已借出，本次借阅失败）
		boolean isLendSuccess = false;
		
		for(int i = 0; i < dvds.length;i++) {
			// 判断编号为no的DVD是否为可借状态
			if( dvds[i] != null && no.equals(dvds[i].getNo()) && dvds[i].getState().equals("可以借")) {
				
				// 成功借阅，更改isLendSuccess为true
				isLendSuccess = true;
				System.out.println("恭喜你！此DVD在系统中的状态为：可以借！");

				// 在存放所有DVD的数组中，将编号为no的DVD状态更改为“已借出”
				dvds[i].setState("已借出");

				// 更新数组数据源
				c.setDVDs(dvds);
				
				break;
			}
		}
		if(!isLendSuccess) {
			System.out.println("对不起！操作不成功！可能因为不存在此编号或此DVD已借出！");
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
				LendDVDManager(dvds);
				break;
			case "n":
				Scanner s1 = new Scanner(System.in); 
				System.out.println("请输入数字0返回：");
				String Choice = s1.nextLine();
				if (Choice.equals("0")) {
					c.ChoiceManager();
				}else {
					System.out.println("输入数字错误，请重新输入！");
					LendDVDManager(dvds);
				}
				break;
			default:
				System.out.println("请输入y或n~");
				break;
			}
		}
	}

}
