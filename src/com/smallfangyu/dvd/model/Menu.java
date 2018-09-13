package com.smallfangyu.dvd.model;

import java.util.ArrayList;
import java.util.Scanner;

import com.smallfangyu.dvd.data.Data;

public class Menu {
    
	 Data data=new Data();
	 //引用Data里面的show方法
	 ArrayList<DVD> dvds =data.show();

	// 初始化DVD编号
	static int no = 0;

	// 初始化DVD名称
	static String dvdname = null;

	// 登陆次数初始化
	static int loginCNT = 3;

	// 初始化输入的账号
	String account = null;

	// 初始化输入的密码
	String password = null;

	// 创建账户密码对象
	User user = new User();

	public void login() {
		System.out.println("**************欢迎使用MiniDVD Mgr 1.0 管理系统**************");
		System.out.println("---------------------------------");
		System.out.println("         1.登陆");
		System.out.println("         2.退出");
		System.out.println("---------------------------------");
		System.out.println("请选择对应数字:");
		// 调用 当输入不在1-3范围或输入非法数字，系统会提示，并进行重新输入
		reinput();
	}

	// 当输入不在1-2范围或输入非法数字，系统会提示，并进行重新输入
	public void reinput() {
		// 判断值
		boolean isReinputEnd = true;
		int n = 0;
		while (isReinputEnd) {
			Scanner scanner = new Scanner(System.in);
			try {
				n = scanner.nextInt();
				scanner.nextLine();
				if (n >= 1 && n <= 2) {
					isReinputEnd = false;
				} else {
					System.out.println("没有这个选项，请再次输入:");
					isReinputEnd = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("输入的不是整数，请再次输入:");
				isReinputEnd = true;
			}
		}
		// 输入数字后两个界面
		switch (n) {
		case 1:
			do {
				System.out.println("请输入用户名:");
				Scanner num = new Scanner(System.in);
				account = num.next();
				if (!(account.equals(user.getUsername()))) {
					// 调用 判断用户名密码是否正确方法
					judge();
				} else {
					do {
						System.out.println("请输入密码:");
						password = num.next();
						if (password.equals(user.getPassword())) {
							// 调用登录后界面
							enter();
						} else {
							// 调用 判断用户名密码是否正确方法
							judge();
						}
					} while (loginCNT > 0);
				}
				if (account.equals(user.getUsername()) && password.equals(user.getPassword())) {
					break;
				}
			} while (loginCNT > 0);
			break;
		case 2:
			System.exit(-1);
		}

	}

	// 登录后界面
	public void enter() {
		System.out.println("**************欢迎进入MiniDVD Mgr 1.0 管理系统**************");
		System.out.println("---------------------------------");
		System.out.println("         1.显示DVD");
		System.out.println("         2.查看DVD");
		System.out.println("         3.借出DVD");
		System.out.println("         4.归还DVD");
		System.out.println("         5.添加DVD");
		System.out.println("         6.删除DVD");
		System.out.println("         7.修改DVD");
		System.out.println("         8.注销");
		System.out.println("---------------------------------");
		System.out.println("请选择对应数字:");
		// 调用 当输入不在1-5范围或输入非法数字，系统会提示，并进行重新输入
		reinputs(null);

	}

	// 显示DVD界面
	public void display() {
		System.out.println("MiniDVD Mgr 1.0 管理系统---->显示当前所有DVD信息");
		System.out.println("编号\t       名称\t\t状态");
		System.out.println("---------------------------------");
		for (DVD dvd : dvds) {
			dvd.show();
		}
		System.out.println("---------------------------------");

	}

	// 按数字0返回
	public void back() {
		System.out.println("请输入数字0返回:");
		Scanner scanner = new Scanner(System.in);
		try {
			int n = scanner.nextInt();
			if (n == 0) {
				enter();
			} else {
				System.out.println("请输入正确的数字");
				back();
			}
		} catch (java.util.InputMismatchException e) {
			System.out.println("请输入正确的数字");
			back();
		}
	}

	// 判断用户名密码是否正确
	public void judge() {
		loginCNT--;
		if (loginCNT > 0) {
			System.out.println("用户名或密码不正确");
			System.out.println("你还有" + loginCNT + "次机会");
		} else {
			System.out.println("对不起，你无权登陆本系统");
		}
	}

	// 按编号查询
	public void noquery() {
		// 选择编号
		System.out.println("请输入要查询的DVD编号:");

		Scanner scanner = new Scanner(System.in);

		try {
			no = scanner.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("输入的不是整数，请再次输入:");
			// 循环按编号查询
			noquery();
		}
		// 判断数组里是否有值
		boolean queryhave = false;
		for (DVD dvd : dvds) {
			if (no == dvd.getId()) {
				queryhave = true;
				System.out.println("编号\t  名称\t    状态");
				System.out.println("---------------------------------");
				dvd.show();
				System.out.println("---------------------------------");
			}
		}
		if (!queryhave) {
			System.out.println("所输入的DVD编号不正确!请重试!");
			noquery();
		}
		// 判断是否继续
		boolean keep = false;
		do {
			System.out.println("是否继续? y/n");
			String py = scanner.next();
			if (py.equals("y")) {
				noquery();
			} else if (py.equals("n")) {
				back();
			} else {
				System.out.println("请输入正确的字母");
				keep = true;
			}
		} while (keep);
	}

	// 按名称查询
	public void dvdnamequery() {

		System.out.println("请输入要查询的DVD名称:");
		Scanner scanner = new Scanner(System.in);
		dvdname = scanner.next();
		// 判断数组里是否有值
		boolean queryhave = false;
		for (DVD dvd : dvds) {
			if (dvdname.equals(dvd.getDvdname())) {
				queryhave = true;
				System.out.println("编号\t  名称\t    状态");
				System.out.println("---------------------------------");
				dvd.show();
				System.out.println("---------------------------------");
			}
		}
		if (!queryhave) {
			System.out.println("所输入的DVD名称不正确!请重试!");
		}

		// 判断Y/N 是否继续按名称查询
		boolean keep = false;
		do {
			System.out.println("是否继续? y/n");
			String py = scanner.next();
			if (py.equals("y")) {
				dvdnamequery();
			} else if (py.equals("n")) {
				back();
			} else {
				System.out.println("请输入正确的字母");
				keep = true;
			}
		} while (keep);
	}

	// 查询DVD界面，实现查询功能
	public void query() {
		System.out.println("MiniDVD Mgr 1.0 管理系统---->查询DVD信息");
		System.out.println("---------------------------------");
		System.out.println("   1.按编号查询");
		System.out.println("   2.按名称查询");
		System.out.println("请选择查询的方式:");
		// 选择编号
		Scanner num = new Scanner(System.in);
		int n = 0;
		// 判断数字是否在范围内或者错误
		boolean isReinputEnd = true;
		while (isReinputEnd) {
			Scanner scanner = new Scanner(System.in);
			try {
				n = scanner.nextInt();
				scanner.nextLine();
				if (n >= 1 && n <= 2) {
					isReinputEnd = false;
				} else {
					System.out.println("没有这个选项，请再次输入:");
					isReinputEnd = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("输入的不是整数，请再次输入:");
				isReinputEnd = true;
			}
		}
		switch (n) {
		case 1:
			noquery();
			break;
		case 2:
			dvdnamequery();
			break;
		}
	}

	// 借出界面，实现借出功能
	public void lend() {
		System.out.println("MiniDVD Mgr 1.0 管理系统---->借出DVD信息");
		System.out.println("请输入要借出的DVD的编号:");
		Scanner scanner = new Scanner(System.in);
		try {
			no = scanner.nextInt();
		} catch (java.util.InputMismatchException e) {

			System.out.println("输入的不是整数，请再次输入:");
			lend();
		}
		// 判断DVD编号是否存在
		// 定义一个判断量
		boolean queryhave = false;
		for (DVD dvd : dvds) {
			if (no == dvd.getId() && (dvd.getState()).equals("可以借")) {
				queryhave = true;
				System.out.println("恭喜您！此DVD在系统中的状态为：可以借！");
				display();
				dvd.setState("可以借");
			}
		}
		if (!queryhave) {
			System.out.println("对不起!操作不成功，可能因为不存在此编号或此DVD已借出!");
		}

		// 判断是否继续
		boolean keep = false;
		do {
			System.out.println("是否继续? y/n");
			String py = scanner.next();
			if (py.equals("y")) {
				lend();
			} else if (py.equals("n")) {
				back();
			} else {
				System.out.println("请输入正确的字母");
				keep = true;
			}
		} while (keep);
	}

	// 归还界面
	public void revert() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要归还的DVD的编号:");
		// 判断输入的DVD编号是否合法
		try {
			no = scanner.nextInt();
			scanner.nextLine();
		} catch (java.util.InputMismatchException e) {
			System.out.println("输入的不是整数，请再次输入:");
			revert();
		}

		System.out.println("请输入要归还的DVD的名称:");
		dvdname = scanner.next();

		for (DVD dvd : dvds) {
			// 判断DVD是否可以归还
			if (no == dvd.getId()) {
				if (dvdname.equals(dvd.getDvdname()) && (dvd.getState().equals("已借出"))) {
					dvd.setState("可以借");
					System.out.println("DVD已归还");
					break;
				} else {
					System.out.println("DVD名称不正确或DVD可以借");
					break;
				}
			} else {
				// 定义新数组来添加新的DVD
				// Dvd[] newdvds=new Dvd[dvds.length+1] ;
				// for(int l=0;l<dvds.length;l++) {
				// newdvds[l]=dvds[l];
				// }
				dvds.add(new DVD(no, dvdname, "可以借"));

				System.out.println("DVD已归还");
				break;
			}
		}
		// 展示归还后的DVD界面
		display();

		// 判断是否继续
		boolean keep = false;
		do {
			System.out.println("是否继续? y/n");
			String py = scanner.next();
			if (py.equals("y")) {
				revert();
			} else if (py.equals("n")) {
				back();
			} else {
				System.out.println("请输入正确的字母");
				keep = true;
			}
		} while (keep);
	}

	// 添加DVD界面,实现添加功能
	public void add() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入添加的DVD编号:");
		try {
			no = scanner.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("输入的不是整数，请再次输入:");
			add();
		}
		// 判断输入的DVD编号是否重复
		for (DVD dvd : dvds) {
			if (no == dvd.getId()) {
				System.out.println("当前DVD编号已存在，请重新输入:");
				add();
			}
		}
		System.out.println("请输入添加的DVD名称:");
		dvdname = scanner.next();

		// 定义新数组添加新的DVD
		// Dvd[] newdvds=new Dvd[dvds.length+1];
		// for(int l=0;l<=dvds.length-1;l++) {
		// newdvds[l]=dvds[l];
		// }
		// newdvds[dvds.length]= new Dvd(no,dvdname,"可以借");
		// dvds=newdvds;

		dvds.add(new DVD(no, dvdname, "可以借"));

		display();
		System.out.println("DVD添加成功");

		// 判断是否继续
		boolean keep = false;
		do {
			System.out.println("是否继续? y/n");
			String py = scanner.next();
			if (py.equals("y")) {
				add();
			} else if (py.equals("n")) {
				back();
			} else {
				System.out.println("请输入正确的字母");
				keep = true;
			}
		} while (keep);
	}

	// 删除DVD界面,实现删除功能
	public void delete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要删除的DVD编号:");
		try {
			no = scanner.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("输入的不是整数，请再次输入:");
			delete();
		}
		// 找出输入的编号在数组中的位置
		int index = 0;
		boolean queryhave = false;
		for (int i = 0; i < dvds.size(); i++) {
			if (no == dvds.get(i).getId()) {
				queryhave = true;
				index = i;
			}
		}
		// 判断输入的DVD编号是否存在
		if (!queryhave) {
			System.out.println("当前输入的DVD编号不存在，请重新输入:");
			delete();
		}

		// 删除DVD
		// for(int l=index;l<dvds.length-1;l++) {
		// dvds[l]=dvds[l+1];
		// }
		dvds.remove(index);

		display();
		System.out.println("DVD删除成功");

		// 判断是否继续
		boolean keep = false;
		do {
			System.out.println("是否继续? y/n");
			String py = scanner.next();
			if (py.equals("y")) {
				delete();
			} else if (py.equals("n")) {
				back();
			} else {
				System.out.println("请输入正确的字母");
				keep = true;
			}
		} while (keep);
	}

	// 实现修改DVD功能
	public void update() {
		Scanner scanner = new Scanner(System.in);
		// 判断输入的DVD编号是否正确
		System.out.println("请选择要修改的DVD编号:");
		try {
			no = scanner.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("输入的不是整数，请再次输入:");
			update();
		}
		// 定义一个 判断输入的DVD编号是否存在 的量
		boolean queryhave = false;
		// 从数组中查找要修改的DVD
		for (DVD dvd : dvds) {
			if (no == dvd.getId()) {
				queryhave = true;

				System.out.println("--------正在修改的DVD信息--------");
				System.out.println("编号\t       名称\t\t状态");
				dvd.show();

				System.out.println("请输入要修改的DVD名称:");
				dvdname = scanner.next();
				dvd.setDvdname(dvdname);

				boolean state = true;
				do {
					System.out.println("请输入要修改的DVD状态:");
					String dvdstate = scanner.next();

					if ((dvdstate.equals("可以借")) || (dvdstate.equals("已借出"))) {
						dvd.setState(dvdstate);
						display();
						state = false;
					} else {
						System.out.println("修改的DVD状态不正确，请重新输入:");
					}
				} while (state);

			}
		}
		// 判断输入的DVD编号是否存在
		if (!queryhave) {
			System.out.println("当前输入的DVD编号不存在，请重新输入:");
			update();
		}

		// 判断是否继续
		boolean keep = false;
		do {
			System.out.println("是否继续? y/n");
			String py = scanner.next();
			if (py.equals("y")) {
				update();
			} else if (py.equals("n")) {
				back();
			} else {
				System.out.println("请输入正确的字母");
				keep = true;
			}
		} while (keep);
	}

	// 当输入不在1-8范围或输入非法数字，系统会提示，并进行重新输入
	public void reinputs(ArrayList<DVD> dvds) {
		// 判断输入的数是否合法
		boolean isReinputsEnd = true;
		int n = 0;
		while (isReinputsEnd) {
			Scanner scanner = new Scanner(System.in);
			try {
				n = scanner.nextInt();
				if (n >= 1 && n <= 8) {
					isReinputsEnd = false;
				} else {
					System.out.println("没有这个选项，请再次输入:");
					isReinputsEnd = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("输入的不是整数，请再次输入:");
				isReinputsEnd = true;
			}
		}
		// 输入数字后选择进入哪个页面
		switch (n) {
		case 1:
			// 调用显示DVD界面
			display();
			back();
			break;

		case 2:
			// 调用查看界面
			query();

			break;
		case 3:
			// 调用借出界面
			lend();
			break;

		case 4:
			// 调用归还界面
			revert();
			break;

		case 5:
			// 调用添加界面
			add();
			break;

		case 6:
			// 调用删除界面
			delete();
			break;

		case 7:
			// 调用修改界面
			update();
			break;

		case 8:
			// 调用登陆界面
			login();
			break;
		}
	}
}
