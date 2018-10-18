package com.zhangmin.DVD;


import java.util.Scanner;

public class Menu {
    //实现查看DVD功能
    public void showDvds(DVDModel dvds[]) {
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
            DVDMain c = new DVDMain();
            c.choiceMenu();
        }else {
            System.out.println("输入数字错误，请重新输入！");
            showDvds(dvds);
        }
    }

    //实现查看dvd功能
    public void checkDvds(DVDModel dvds[]) {
        System.out.println("MiniDVD Mgr 1.0 管理系统---->查询DVD信息");
        System.out.println("----------------------------------");
        System.out.println("-------------1.按编号查询------------");
        System.out.println("-------------2.按名称查询------------");
        System.out.println("---------------0.返回--------------");
        System.out.println("----------------------------------");
        System.out.println("请选择查询的方式：");

        Scanner n = new Scanner(System.in);
        int num = n.nextInt();
        if (num == 1) {
            System.out.println("请输入DVD编号");
            Scanner s = new Scanner(System.in);
            String s1 = s.nextLine();

            // 标记查找的DVD是否存在（默认不存在）
            boolean isExist = false;
            for (int i = 0; i < dvds.length; i++) {
                if (dvds[i] != null && s1.equals(dvds[i].getNo())) {
                    //存在即修改标记
                    isExist = true;
                    System.out.println("编号\t名称\t\t状态");
                    System.out.println(dvds[i].getNo() + "\t" + dvds[i].getName() + "\t" + dvds[i].getState());
                    break;
                }
            }
            if (!isExist) {
                System.out.println("所输入的DVD编号不正确！请重试！");
                checkDvds(dvds);
            }
            while (true) {
                System.out.println("是否继续？ y/n");
                Scanner s11 = new Scanner(System.in);
                String yn = s11.nextLine();
                switch (yn) {
                    case "y":
                        checkDvds(dvds);
                        break;
                    case "n":
                        System.out.println("请输入数字0返回：");
                        Scanner s2 = new Scanner(System.in);
                        String Choice1 = s2.nextLine();
                        if (Choice1.equals("0")) {
                            DVDMain c = new DVDMain();
                            c.choiceMenu();
                        } else {
                            System.out.println("输入数字错误，请重新输入！");
                            checkDvds(dvds);
                        }
                        break;
                    default:
                        System.out.println("请输入y或n");
                        break;
                }
            }
        } else if (num == 2) {
            Scanner s1 = new Scanner(System.in);
            System.out.println("请输入要查询的DVD名称：");
            String name = s1.nextLine();

            boolean isExist = false;

            for (int i = 0; i < dvds.length; i++) {
                if (dvds[i] != null && name.equals(dvds[i].getName())) {
                    isExist = true;
                    System.out.println("编号\t名称\t\t状态");
                    System.out.println(dvds[i].getNo() + "\t" + dvds[i].getName() + "\t" + dvds[i].getState());
                    //break;
                }
            }
            if (!isExist) {
                System.out.println("此DVD不存在！");
                checkDvds(dvds);
            }
            while (true) {
                System.out.println("是否继续？ y/n");
                Scanner s11 = new Scanner(System.in);
                String yn = s11.nextLine();
                switch (yn) {
                    case "y":
                        checkDvds(dvds);
                        break;
                    case "n":
                        System.out.println("请输入数字0返回：");
                        Scanner s2 = new Scanner(System.in);
                        String Choice1 = s2.nextLine();
                        if (Choice1.equals("0")) {
                            DVDMain c = new DVDMain();
                            c.choiceMenu();
                        } else {
                            System.out.println("输入数字错误，请重新输入！");
                            checkDvds(dvds);
                        }
                        break;
                    default:
                        System.out.println("请输入y或n");
                        break;
                }
            }
        } else if (num == 0) {
            DVDMain chdvd = new DVDMain();
            chdvd.choiceMenu();
        } else {
            System.out.println("输入错误，请重新输入：");
            checkDvds(dvds);
        }
    }

    //实现借出DVD功能
    public  void lendDvds(DVDModel dvds[]){
        DVDMain dvdMain = new DVDMain();

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
                dvdMain.setDVD(dvds);

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
                    lendDvds(dvds);
                    break;
                case "n":
                    Scanner s1 = new Scanner(System.in);
                    System.out.println("请输入数字0返回：");
                    String Choice = s1.nextLine();
                    if (Choice.equals("0")) {
                        dvdMain.choiceMenu();
                    }else {
                        System.out.println("输入数字错误，请重新输入！");
                        lendDvds(dvds);
                    }
                    break;
                default:
                    System.out.println("请输入y或n~");
                    break;
            }
        }
    }

    //实现归还DVD功能
    public  void returnDvds(DVDModel dvds[]){
        DVDMain c= new DVDMain();
        System.out.println("请输入要归还DVD的编号：");
        Scanner s = new Scanner(System.in);
        String no = s.nextLine();

        System.out.println("请输入要归还DVD的名称：");
        Scanner s1 = new Scanner(System.in);
        String name = s1.nextLine();

        boolean isReturnSuccess = true;
        boolean isDVDSuccess  = true;
        for (int i = 0; i <dvds.length ; i++) {
            // 判断编号为no的DVD是否为借出状态
            if( dvds[i] != null && no.equals(dvds[i].getNo()) && name.equals(dvds[i].getName()) && dvds[i].getState().equals("已借出")) {
                System.out.println("恭喜你！归还成功~");
                // 在存放所有DVD的数组中，将编号为no的DVD状态更改为“可以借”
                dvds[i].setState("可以借");
                // 更新数组数据源
                c.setDVD(dvds);
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
                    returnDvds(dvds);
                    break;
                case "n":
                    Scanner s12 = new Scanner(System.in);
                    System.out.println("请输入数字0返回：");
                    String Choice = s12.nextLine();
                    if (Choice.equals("0")) {
                        c.choiceMenu();
                    }else {
                        System.out.println("输入数字错误，请重新输入！");
                        returnDvds(dvds);
                    }
                    break;
                default:
                    System.out.println("请输入y或n~");
                    break;
            }
        }

    }

    //实现添加DVD功能
    public void addDvds(DVDModel dvds[]){
        System.out.println("请输入要添加DVD的编号：");
        Scanner s = new Scanner(System.in);
        String no = s.nextLine();

        System.out.println("请输入要添加DVD的名称：");
        Scanner s1 = new Scanner(System.in);
        String name = s1.nextLine();

        DVDMain c = new DVDMain();
        //将info转为dvd存储
         DVDModel dvd = new DVDModel(no, name, "可以借");

        // 将归还的DVD添加到存放所有DVD的数组中
        for(int i = 0; i < dvds.length; i++) {
            if(dvds[i] == null) {
                dvds[i] = dvd;
                // 添加成功后，要更新数组数据源
                c.setDVD(dvds);
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
                   addDvds(dvds);
                    break;
                case "n":
                    Scanner s12 = new Scanner(System.in);
                    System.out.println("请输入数字0返回：");
                    String Choice = s12.nextLine();
                    if (Choice.equals("0")) {
                        c.choiceMenu();
                    }else {
                        System.out.println("输入数字错误，请重新输入！");
                       addDvds(dvds);
                    }
                    break;
                default:
                    System.out.println("请输入y或n~");
                    break;
            }
        }
    }

    //实现修改DVD功能
    public  void modifyDvds(DVDModel dvds[]){
        DVDMain c = new DVDMain();

        System.out.println("请输入要修改DVD的编号：");
        Scanner s = new Scanner(System.in);
        String no = s.nextLine();

        System.out.println("请输入要修改DVD的名称：");
        Scanner s1 = new Scanner(System.in);
        String name = s1.nextLine();

        // 标记是否成功借到DVD（默认DVD可以借，本次借阅失败）
        boolean isReturnSuccess = true;
        boolean isDVDSuccess = true;
        for(int i = 0; i < dvds.length;i++) {
            // 判断编号为no的DVD是否为借出状态
            if( dvds[i] != null && no.equals(dvds[i].getNo()) && name.equals(dvds[i].getName())) {
                System.out.println("恭喜你！可以修改DVD信息~");
                // 在存放所有DVD的数组中，将编号为no的DVD状态更改为“可以借”
                System.out.println("请输入新的DVD的编号：");
                Scanner s11 = new Scanner(System.in);
                String no1 = s11.nextLine();

                System.out.println("请输入新的DVD的名称：");
                Scanner s111 = new Scanner(System.in);
                String name1 = s111.nextLine();

                dvds[i].setNo(no1);
                dvds[i].setName(name1);
                dvds[i].setState("可以借");
                // 更新数组数据源
                c.setDVD(dvds);
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
                    modifyDvds(dvds);
                    break;
                case "n":
                    Scanner s12 = new Scanner(System.in);
                    System.out.println("请输入数字0返回：");
                    String Choice = s12.nextLine();
                    if (Choice.equals("0")) {
                        c.choiceMenu();
                    }else {
                        System.out.println("输入数字错误，请重新输入！");
                        modifyDvds(dvds);
                    }
                    break;
                default:
                    System.out.println("请输入y或n~");
                    break;
            }
        }
    }

    //实现删除DVD功能
    public void deleteDvds(DVDModel dvds[]){
        DVDMain c = new DVDMain();

        System.out.println("请输入要删除DVD的编号：");
        Scanner s = new Scanner(System.in);
        String no = s.nextLine();

        System.out.println("请输入要删除DVD的名称：");
        Scanner s1 = new Scanner(System.in);
        String name = s1.nextLine();

        // 标记是否成功借到DVD（默认DVD可以借，本次借阅失败）
        boolean isReturnSuccess = true;
        boolean isDVDSuccess = true;
        for(int i = 0; i < dvds.length;i++) {
            // 判断编号为no的DVD是否为借出状态
            if( dvds[i] != null && no.equals(dvds[i].getNo()) && name.equals(dvds[i].getName())) {
                System.out.println("恭喜你！删除成功~");
                dvds[i].setName("此处书已被删除。");
                dvds[i].setState("");
                // 更新数组数据源
                c.setDVD(dvds);
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
                    deleteDvds(dvds);
                    break;
                case "n":
                    Scanner s12 = new Scanner(System.in);
                    System.out.println("请输入数字0返回：");
                    String Choice = s12.nextLine();
                    if (Choice.equals("0")) {
                        c.choiceMenu();
                    }else {
                        System.out.println("输入数字错误，请重新输入！");
                        deleteDvds(dvds);
                    }
                    break;
                default:
                    System.out.println("请输入y或n~");
                    break;
            }
        }
    }

    //实现注销功能
//    public void disUser(DVDModel dvds[]){
//        System.out.println("MiniDVD Mgr 1.0 管理系统---->显示当前所有DVD信息");
//        System.out.println("编号\t名称\t\t状态");
//
//        for(int i = 0; i < dvds.length; i++) {
//            if(dvds[i] != null) {
//                System.out.println(dvds[i].getNo() + "\t" + dvds[i].getName() + "\t" + dvds[i].getState());
//            }
//        }
//
//        System.out.println("请输入数字0返回：");
//        Scanner s = new Scanner(System.in);
//        int Choice = s.nextInt();
//        if (Choice == 0) {
//            DVDMain c = new DVDMain();
//            c.choiceMenu();
//        }else {
//            System.out.println("输入数字错误，请重新输入！");
//            disUser(dvds);
//        }
//    }

}
