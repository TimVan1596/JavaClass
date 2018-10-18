package com.zhangmin.DVD;

import java.util.Scanner;

public class DVDMain {
    private static DVDModel[] dvds = new DVDModel[10];
   static{
          showDVD();
   }
    public static void showDVD() {
        //存储已有数组
        dvds[0] = new DVDModel("1000", "《大校的女儿》", "已借出");
        dvds[1] = new DVDModel("1001", "《恰同学少年》", "可以借");
        dvds[2] = new DVDModel("1002", "《Java》", "已借出");
        dvds[3] = new DVDModel("1003", "《C++》", "可以借");
    }

    public void setDVD(DVDModel dvd[]) {
        this.dvds = dvd;
    }

    //DVD菜单
    public void choiceMenu() {
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
        Scanner n = new Scanner(System.in);
        int choice = n.nextInt();
        Menu menu = new Menu();
        switch (choice){
            case 1:
                //显示DVD
                menu.showDvds(dvds);
                break;
            case 2:
                //查看DVD
                menu.checkDvds(dvds);
                break;
            case 3:
                menu.lendDvds(dvds);
                //借出DVD
                break;
            case 4:
                //归还DVD
                menu.returnDvds(dvds);
                break;
            case 5:
                //添加DVD
                menu.addDvds(dvds);
                break;
            case 6:
                //修改DVD
                menu.modifyDvds(dvds);
                break;
            case 7:
                //删除DVD
                menu.deleteDvds(dvds);
                break;
            case 0:
                //注销
                Login ln = new Login();
                ln.loginManager();
                break;
            default:
                    System.out.println("输入有误，请重新输入：");
                    choiceMenu();
                    break;

        }


    }
}
