package com.antianbao.historydvd.MinDVD.choice;

import java.util.Scanner;

import com.antianbao.historydvd.MinDVD.login.Choice;
import com.antianbao.historydvd.MinDVD.main.DVD;

public class ReturnDVD {

	@SuppressWarnings("resource")
	public void ReturnDVDManager(DVD[] dvds) {
		// TODO Auto-generated method stub

		Choice c = new Choice();

		System.out.println("请输入要归还DVD的编号：");
		Scanner s = new Scanner(System.in); 
		String no = s.nextLine();

		System.out.println("请输入要归还DVD的名称：");
		Scanner s1 = new Scanner(System.in); 
		String name = s1.nextLine();

		// 标记是否成功借到DVD（默认DVD可以借，本次借阅失败）
		boolean isReturnSuccess = true;
		boolean isDVDSuccess = true;
		for(int i = 0; i < dvds.length;i++) {
			// 判断编号为no的DVD是否为借出状态
			if( dvds[i] != null && no.equals(dvds[i].getNo()) && name.equals(dvds[i].getName()) && dvds[i].getState().equals("已借出")) {
				System.out.println("恭喜你！归还成功~");
				// 在存放所有DVD的数组中，将编号为no的DVD状态更改为“可以借”
				dvds[i].setState("可以借");
				// 更新数组数据源
				c.setDVDs(dvds);
				isDVDSuccess = false;
				break;
			}else if(dvds[i] != null && no.equals(dvds[i].getNo()) && name.equals(dvds[i].getName()) && dvds[i].getState().equals("可以借")) {
				System.out.println("您输入的DVD处于可以借状态~");
				isReturnSuccess = false;
				isDVDSuccess = false;
				break;
			}else if(dvds[i] != null && (no.equals(dvds[i].getNo()) || name.equals(dvds[i].getName()))) {
				System.out.println("DVD编号或名称错误~");
				isReturnSuccess = false;
				isDVDSuccess = false;
				break;
			}
		}
		if(isDVDSuccess) {
			System.out.println("您输入的DVD不存在~");
			isReturnSuccess = false;
		}
		if(isReturnSuccess) {
			System.out.println("MiniDVD Mgr 1.0 管理系统---->显示当前所有DVD信息");
			System.out.println("编号\t名称\t\t状态");
			for(int i = 0; i < dvds.length; i++) {
				if(dvds[i] != null) {
					System.out.println(dvds[i].getNo() + "\t" + dvds[i].getName() + "\t" + dvds[i].getState());
				}
			}
		}

		while(true) {
			System.out.println("是否继续？ y/n");
			Scanner s11 = new Scanner(System.in); 
			String yn = s11.nextLine();
			switch(yn) {
			case "y":
				ReturnDVDManager(dvds);
				break;
			case "n":
				Scanner s12 = new Scanner(System.in); 
				System.out.println("请输入数字0返回：");
				String Choice = s12.nextLine();
				if (Choice.equals("0")) {
					c.ChoiceManager();
				}else {
					System.out.println("输入数字错误，请重新输入！");
					ReturnDVDManager(dvds);
				}
				break;
			default:
				System.out.println("请输入y或n~");
				break;
			}
		}
	}

}