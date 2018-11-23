package com.antianbao.historydvd.MinDVD.login;

import java.util.Scanner;

import com.antianbao.historydvd.MinDVD.choice.AddDVD;
import com.antianbao.historydvd.MinDVD.choice.CheckDVD;
import com.antianbao.historydvd.MinDVD.choice.DeleteDVD;
import com.antianbao.historydvd.MinDVD.choice.DisplayDVD;
import com.antianbao.historydvd.MinDVD.choice.LendDVD;
import com.antianbao.historydvd.MinDVD.choice.ModifyDVD;
import com.antianbao.historydvd.MinDVD.choice.ReturnDVD;
import com.antianbao.historydvd.MinDVD.main.DVD;

/*DisplayDVD 显示DVD
 * CheckDVD 查看DVD
 * LendDVD 借出DVD
 * ReturnDVD 归还DVD
 * */
public class Choice {
	private DVD[] dvds = new DVD[10];
	public Choice() {
		dvds[0] = new DVD("1000","《大校的女儿》","已借出");
		dvds[1] = new DVD("1001","《恰同学少年》","可以借");
		dvds[2] = new DVD("1002","《这是本书哦》","已借出");
		dvds[3] = new DVD("1003","《这是本书哦》","可以借");
	}

	public void setDVDs(DVD[] dvds) {
		this.dvds = dvds;
	}

	@SuppressWarnings("resource")
	public void ChoiceManager() {
		System.out.println("********欢迎进入MiniDVD Mgr 1.0 管理系统********");
		System.out.println("------------------------------------------");
		System.out.println("------------------1.显示DVD-----------------");
		System.out.println("------------------2.查看DVD-----------------");
		System.out.println("------------------3.借出DVD-----------------");
		System.out.println("------------------4.归还DVD-----------------");
		System.out.println("------------------5.添加DVD-----------------");
		System.out.println("------------------6.修改DVD-----------------");
		System.out.println("------------------7.删除DVD-----------------");
		System.out.println("------------------0.注销-------------------");
		System.out.println("------------------------------------------");
		System.out.println("请输入对应数字选择：");

		Scanner s = new Scanner(System.in); 
		String Choice = s.nextLine();

		switch (Choice) {
		case "1":
			DisplayDVD Ddvd = new DisplayDVD();
			Ddvd.DisplayDVDManager(dvds);
			break;
		case "2":
			CheckDVD Cdvd = new CheckDVD();
			Cdvd.CheckDVDManager(dvds);
			break;
		case "3":
			LendDVD Ldvd = new LendDVD();
			Ldvd.LendDVDManager(dvds);
			break;
		case "4":
			ReturnDVD Rdvd = new ReturnDVD();
			Rdvd.ReturnDVDManager(dvds);
			break;
		case "5":
			AddDVD Advd = new AddDVD();
			Advd.AddDVDManager(dvds);
			break;
		case "6":
			ModifyDVD Mdvd = new ModifyDVD();
			Mdvd.ModifyDVDManager(dvds);
			break;
		case "7":
			DeleteDVD Dedvd = new DeleteDVD();
			Dedvd.DeleteDVDManager(dvds);
			break;
		case "0":
			Login ln = new Login();
			ln.loginManager();
			break;
		default:
			System.out.println("输入有误，请重新输入！");
			ChoiceManager();
			break;
		}
	}
}