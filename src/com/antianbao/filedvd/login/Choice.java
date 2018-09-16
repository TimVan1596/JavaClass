package com.antianbao.filedvd.login;

import com.antianbao.jhdvd.main.Dvd;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 *
 * DisplayDVD 显示
 * CheckDVD 查看
 * LendDVD 借出
 * ReturnDVD 归还
 * AddDVD 添加
 * ModifyDVD 修改
 * DeleteDVD 删除
 */
public class Choice {
    static ArrayList<Dvd> dvds = new ArrayList<>();
    //数据的初始化
    static {
        Dvd dvd1 = new Dvd(1000, "《大校的女儿》", "已借出");
        Dvd dvd2 = new Dvd(1001, "《恰同学少年》", "可以借");
        Dvd dvd3 = new Dvd(1002, "《士兵突击》", "已借出");
        Dvd dvd4 = new Dvd(1003, "《士兵突击》", "可以借");
        dvds.add(dvd1);
        dvds.add(dvd2);
        dvds.add(dvd3);
        dvds.add(dvd4);
    }

    public void ChoiceManager() {
        System.out.println("********欢迎进入MiniDVD Mgr 2.0 管理系统********");
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
        //菜单栏选择操作
        switch (Choice) {
            case "1":
                DisplayDVD();
                break;
            case "2":
                CheckDVD();
                break;
            case "3":
                LendDVD();
                break;
            case "4":
                ReturnDVD();
                break;
            case "5":
                AddDVD();
                break;
            case "6":
                ModifyDVD();
                break;
            case "7":
                DeleteDVD();
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
    /**
     * 判断是否继续y/n
     */
    public void yn(int a) {
        while (true) {
            System.out.println("-- 是否继续？ y/n --");
            Scanner s11 = new Scanner(System.in);
            String yn = s11.nextLine();
            switch (yn) {
                case "y":
                    switch (a) {
                        case 2:
                            CheckDVD();
                            break;
                        case 3:
                            LendDVD();
                            break;
                        case 4:
                            ReturnDVD();
                            break;
                        case 5:
                            AddDVD();
                            break;
                        case 6:
                            ModifyDVD();
                            break;
                        case 7:
                            DeleteDVD();
                            break;
                        default:
                            break;
                    }
                    break;
                case "n":
                    System.out.println("-----1.返回至菜单-----");
                    System.out.println("-----2.退出此程序-----");
                    Scanner s2 = new Scanner(System.in);
                    int choice = s2.nextInt();
                    try{
                        if ( choice == 1 ) {
                            ChoiceManager();
                        } else if(choice == 2){
                            System.exit(-1);
                        }
                    }catch(Exception e){
                        System.out.println("请输入正确的选择！");
                    }
                    break;
                default:
                    System.out.println("请输入y或n~");
                    break;
            }
        }
    }

    private void DisplayDVD() {
        System.out.println("MiniDVD Mgr 2.0 管理系统---->显示当前所有DVD信息");
        System.out.println("编号\t\t名称\t\t\t状态");
        for (Dvd dvd : dvds) {
            System.out.println(dvd);
        }
        System.out.println("请输入数字0返回：");
        Scanner s2 = new Scanner(System.in);
        int choice = s2.nextInt();
        try {
            if (choice == 0) {
                ChoiceManager();
            } else {
                System.out.println("输入数字错误，请重新输入！");
            }
        } catch (Exception e) {
            System.out.println("请输入正确的数字~");
        }
    }

    private void CheckDVD() {
        System.out.println("MiniDVD Mgr 2.0 管理系统---->查询DVD信息");
        System.out.println("----------------------------------");
        System.out.println("-------------1.按编号查询------------");
        System.out.println("-------------2.按名称查询------------");
        System.out.println("---------------0.返回--------------");
        System.out.println("----------------------------------");
        System.out.println("请选择查询的方式：");


        Scanner s = new Scanner(System.in);
        String Choice = s.nextLine();
        //判断选择的操作 1或2
        switch (Choice){
            case "1":
                System.out.println("请输入要查询的DVD编号：");
                Scanner s1 = new Scanner(System.in);
                int no = s1.nextInt();
                // 标记查找的DVD是否存在（默认不存在）
                boolean isExist = false;
                try {
                    for (Dvd dvd : dvds) {
                        if (no == dvd.getNo()) {
                            // 存在，更改标记为true
                            isExist = true;
                            System.out.println("编号\t名称\t\t状态");
                            System.out.println(dvd);
                        }
                    }
                } catch (Exception e) {
                    System.out.print("请输入DVD编号~");
                    isExist = true;
                }
                // 不存在
                if (!isExist) {
                    System.out.println("所输入的DVD编号不正确！请重试！");
                }
                yn(2);
                break;
            case "2":
                Scanner s2 = new Scanner(System.in);
                System.out.println("请输入要查询的DVD名称(不用加书名号)：");
                String name = "《" + s2.nextLine() + "》";
                boolean isExist1 = false;
                for (Dvd dvd : dvds) {
                    if (name.equals(dvd.getName())) {
                        // 存在，更改标记为true
                        isExist1 = true;
                        System.out.println("编号\t名称\t\t状态");
                        System.out.println(dvd);
                    }
                }
                //输入错误
                if (!isExist1) {
                    System.out.println("此DVD不存在！");
                }
                //判断是否继续
                yn(2);
                break;
            case "0":
                ChoiceManager();
                break;
            default:
                //选择操作错误
                System.out.println("请输入正确的选择~");
                CheckDVD();
                break;
        }
    }

    private void LendDVD() {
        System.out.println("MiniDVD Mgr 2.0 管理系统---->借出DVD信息");
        System.out.println("请输入要借出的DVD编号：");

        Scanner s = new Scanner(System.in);
        int no = s.nextInt();
        // 标记是否成功借到DVD（默认DVD已借出，本次借阅失败）
        boolean isLendSuccess = true;
        //异常判断
        try {
            for (Dvd dvd : dvds) {
                if (no == dvd.getNo() && dvd.getState().equals("可以借")) {
                    // 存在，更改标记为true
                    isLendSuccess = false;
                    System.out.println("恭喜你！此DVD在系统中的状态为：可以借！");
                    // 在存放所有DVD的数组中，将编号为no的DVD状态更改为“已借出”
                    dvd.setState("已借出");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print("请输入对应数字~");
            isLendSuccess = false;
        }
        //判断输入是否正确
        if (isLendSuccess) {
            System.out.println("对不起！操作不成功！可能因为不存在此编号或此DVD已借出！");
        }
        //操作结束后
        System.out.println("MiniDVD Mgr 2.0 管理系统---->显示当前所有DVD信息");
        System.out.println("编号\t名称\t\t状态");
        for (Dvd dvd : dvds) {
            System.out.println(dvd);
        }
        yn(3);
    }

    private void ReturnDVD() {
        System.out.println("请输入要归还DVD的编号：");
        Scanner s = new Scanner(System.in);
        int no = s.nextInt();

        System.out.println("请输入要归还DVD的名称（不用打书名号）：");
        Scanner s1 = new Scanner(System.in);
        String name = "《" + s1.nextLine() + "》";

        // 标记是否成功归还到DVD
        boolean isReturnSuccess = true;
        boolean isDVDSuccess = true;
        //异常判断
        try {
            for (Dvd dvd : dvds) {
                // 判断编号为no的DVD是否为借出状态
                if (no == dvd.getNo() && name.equals(dvd.getName()) && dvd.getState().equals("已借出")) {
                    System.out.println("恭喜你！归还成功~");
                    // 在存放所有DVD的集合中，将编号为no的DVD状态更改为“可以借”
                    dvd.setState("可以借");
                    isDVDSuccess = false;
                    break;
                } else if (no == dvd.getNo() && name.equals(dvd.getName()) && dvd.getState().equals("可以借")) {
                    System.out.println("您输入的DVD处于可以借状态~");
                    isReturnSuccess = false;
                    isDVDSuccess = false;
                    break;
                } else if (no == dvd.getNo() || name.equals(dvd.getName())) {
                    System.out.println("DVD编号或名称错误~");
                    isReturnSuccess = false;
                    isDVDSuccess = false;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print("请输入对应数字~");
            isReturnSuccess = false;
            isDVDSuccess = false;
        }
        //判断输入是否正确
        if (isDVDSuccess) {
            System.out.println("您输入的DVD不存在~");
            isReturnSuccess = false;
        }
        //操作成功后
        if (isReturnSuccess) {
            System.out.println("MiniDVD Mgr 2.0 管理系统---->显示当前所有DVD信息");
            System.out.println("编号\t名称\t\t状态");
            for (Dvd dvd : dvds) {
                System.out.println(dvd);
            }
        }
        //判断是否继续
        yn(4);
    }

    private void AddDVD() {
        int no = 0;
        for (int i = 0; i < dvds.size(); i++) {
            Dvd dvd = dvds.get(i);
            if(i == dvds.size() - 1){
                no = dvd.getNo() + 1;
            }
        }
        System.out.println("请输入要添加DVD的名称：");
        Scanner s1 = new Scanner(System.in);
        String name = "《" + s1.nextLine() + "》";

        // 将添加的DVD添加到存放所有DVD的集合中
        Dvd dvd1 = new Dvd(no, name, "可以借");
        dvds.add(dvd1);

        System.out.println("MiniDVD Mgr 2.0 管理系统---->显示当前所有DVD信息");
        System.out.println("编号\t名称\t\t状态");
        for (Dvd dvd : dvds) {
            System.out.println(dvd);
        }
        //判断是否继续
        yn(5);
    }

    private void ModifyDVD() {
        System.out.println("请输入要修改DVD的编号：");
        Scanner s = new Scanner(System.in);
        int no = s.nextInt();

        System.out.println("请输入要修改DVD的名称（不用加书名号）：");
        Scanner s1 = new Scanner(System.in);
        String name = "《" + s1.nextLine() + "》";

        boolean isReturnSuccess = true;
        boolean isDVDSuccess = true;
        //判断输入是否正确
        for (Dvd dvd : dvds) {
            if (no == dvd.getNo() && name.equals(dvd.getName())) {
                System.out.println("恭喜你！可以修改DVD信息~");
                System.out.println("请输入新的DVD的名称：");
                Scanner s11 = new Scanner(System.in);
                String name1 = "《" + s11.nextLine() + "》";
                dvd.setName(name1);
                dvd.setState("可以借");
                isDVDSuccess = false;
                break;
            } else if (no == dvd.getNo() || name.equals(dvd.getName())) {
                System.out.println("DVD编号或名称错误~");
                isReturnSuccess = false;
                isDVDSuccess = false;
                break;
            }
        }
        //没找到输入的数据
        if (isDVDSuccess) {
            System.out.println("您输入的DVD不存在~");
            isReturnSuccess = false;
        }
        //操作成功后
        if (isReturnSuccess) {
            System.out.println("MiniDVD Mgr 2.0 管理系统---->显示当前所有DVD信息");
            System.out.println("编号\t名称\t\t状态");
            for (Dvd dvd : dvds) {
                System.out.println(dvd);
            }
        }
        //判断是否继续
        yn(6);
    }

    private void DeleteDVD() {
        System.out.println("请输入要删除DVD的编号：");
        Scanner s = new Scanner(System.in);
        int no = s.nextInt();

        System.out.println("请输入要删除DVD的名称（不用打书名号）：");
        Scanner s1 = new Scanner(System.in);
        String name = "《" + s1.nextLine() + "》";

        boolean isReturnSuccess = true;
        boolean isDVDSuccess = true;
        //判断输入是否正确
        for (int i = 0; i < dvds.size(); i++) {
            Dvd dvd = dvds.get(i);
            if (no == dvd.getNo() && name.equals(dvd.getName())) {
                System.out.println("恭喜你！删除成功~");
                dvds.remove(i);
                isDVDSuccess = false;
                break;
            } else if (no == dvd.getNo() || name.equals(dvd.getName())) {
                System.out.println("DVD编号或名称错误~");
                isReturnSuccess = false;
                isDVDSuccess = false;
                break;
            }
        }
        //没找到输入的数据
        if (isDVDSuccess) {
            System.out.println("您输入的DVD不存在~");
            isReturnSuccess = false;
        }
        //操作成功后
        if (isReturnSuccess) {
            System.out.println("MiniDVD Mgr 2.0 管理系统---->显示当前所有DVD信息");
            System.out.println("编号\t名称\t\t状态");
            for (Dvd dvd : dvds) {
                System.out.println(dvd);
            }
        }
        //是否继续判断
        yn(7);
    }
}