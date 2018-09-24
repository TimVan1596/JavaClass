package com.smallfangyu.jdbcdvd.model;

import com.smallfangyu.jdbcdvd.data.Data;
import com.smallfangyu.jdbcdvd.data.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>包含常用的增删改查操作</p>
 * <ul>本数据库操作的步骤
 * <li>注册JDBC驱动</li>
 * <li>获取数据库连接</li>
 * <li>执行数据库的增删改查操作</li>
 * <li>释放资源</li>
 * </ul>
 *
 * @author FY
 * @date 2018-9-24 23:11:35
 * @since 4.0
 */
public class Menu {

    Data data = new Data();

    ArrayList<DVD> dvds;

    DbUtil db = new DbUtil();

    /**
     * 初始化DVD编号
     */
    static int no = 0;

    /**
     * 初始化DVD名称
     */
    static String dvdname = null;

    static String dvdstate = null;
    /**
     * 登陆次数初始化
     */
    static int loginCNT = 3;

    /**
     * 初始化输入的账号
     */
    String account = null;

    /**
     * 初始化输入的密码
     */
    String password = null;

    public Menu() {
        //从文件中读取所有的账户-密码信息
        //serializeLoadAccountToFile();
    }

    //主界面
    public void login() {
        System.out.println("**************欢迎使用MiniDVD Mgr 数据库版 管理系统**************");
        System.out.println("---------------------------------");
        System.out.println("         1.登陆");
        System.out.println("         2.注册");
        System.out.println("         3.找回密码");
        System.out.println("         4.退出");

        System.out.println("---------------------------------");
        System.out.println("请选择对应数字:");
        // 调用 当输入不在1-4范围或输入非法数字，系统会提示，并进行重新输入
        reinput();
    }

    /**
     * 判断用户名是否存在
     */
    public void reUser() {
        String sql = "SELECT * FROM user ";
        ResultSet rs = db.executeQuery(sql, null);
        try {
            //判断用户名是否存在
            while (rs.next()) {
                if (!account.equals(rs.getString("username"))) {
                    //遍历到rs的最后位置
                    if (rs.isLast()) {
                        System.out.println("用户名不存在");
                        userLogin();
                    }
                } else {

                    break;
                }
            }
            //关闭资源
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户登陆
     */
    public void userLogin() {
        Scanner num = new Scanner(System.in);
        System.out.println("请输入用户名:");
        account = num.nextLine();

        //调用判断用户名是否存在
        reUser();

        boolean jud = true;
        do {
            System.out.println("请输入密码:");
            password = num.nextLine();
            String sql1 = "SELECT * FROM user WHERE username=? ";
            Object[] params = {account};
            ResultSet rs1 = db.executeQuery(sql1, params);
            try {
                while (rs1.next()) {
                    if (!password.equals(rs1.getString("password"))) {
                        System.out.println("密码错误");
                    } else {
                        jud = false;
                    }
                }
                //关闭资源
                rs1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } while (jud);
        //关闭资源
        db.close();

        // 调用登录后界面
        enter();

    }

    /**
     * 找回密码
     */
    public void findPassWord() {
        System.out.println("--------找回密码--------");
        System.out.println("请输入你的账号:");
        Scanner scanner = new Scanner(System.in);
        account = scanner.nextLine();

        //调用判断用户名是否存在
        //reUser();

        String sql1 = "SELECT password FROM user WHERE username=? ";
        Object[] params = {account};
        ResultSet rs = db.executeQuery(sql1, params);

        try {
            while (rs.next()) {
                System.out.println("你的账号密码为：" + rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("返回主界面中，请稍等...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        login();
    }


    /**
     * 注册功能
     */
    public void register() {

        System.out.println("请输入新的用户名:");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();

        String sql = "SELECT username FROM user ";
        ResultSet rs = db.executeQuery(sql, null);

        try {
            while (rs.next()) {
                //判断用户名是否重复
                if ((rs.getString("username")).equals(user)) {
                    System.out.println("用户名重复，请重新输入");
                    register();
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("请输入密码:");
        String password = scanner.nextLine();

        //往数据库里插入数据
        String sql1 = "INSERT INTO user VALUES(?,?)";
        Object[] params = {user, password};
        int rlt = db.executeUpdate(sql1, params);
        if (rlt > 0) {
            System.out.println("注册成功");
        }
        db.close();
        // 判断是否继续
        boolean keep = false;
        do {
            System.out.println("是否继续? y/n");
            String py = scanner.next();
            if (py.equals("y")) {
                register();
            } else if (py.equals("n")) {
                login();
            } else {
                System.out.println("请输入正确的字母");
                keep = true;
            }
        } while (keep);

    }

    /**
     * 当输入不在1-4范围或输入非法数字，系统会提示，并进行重新输入
     */
    public void reinput() {
        // 判断值
        boolean isReinputEnd = true;
        int n = 0;

        ArrayList<User> users = null;
        while (isReinputEnd) {
            Scanner scanner = new Scanner(System.in);
            try {
                n = scanner.nextInt();
                scanner.nextLine();
                if (n >= 1 && n <= 4) {
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
                //用户登陆
                userLogin();
                break;
            case 2:
                //注册
                register();
                break;
            case 3:
                //找回密码
                findPassWord();
            case 4:
                System.exit(-1);
        }

    }

    /**
     * 登录后界面
     */
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
        reinputs();

    }

    /**
     * 显示DVD界面
     */
    public void display() {
        System.out.println("MiniDVD Mgr 1.0 管理系统---->显示当前所有DVD信息");
        System.out.println("编号\t       名称\t       状态");
        System.out.println("---------------------------------");
        //清空集合里的数据
        dvds.clear();
        dvds = data.dvdList();
        for (DVD dvd : dvds) {
            dvd.show();
        }

        System.out.println("---------------------------------");

    }

    /**
     * 按数字0返回
     */
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

    /**
     * 按编号查询
     */
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
        dvds = data.dvdList();
        for (DVD dvd : dvds) {
            if (no == dvd.getId()) {
                queryhave = true;
                System.out.println("编号\t  名称\t    状态");
                System.out.println("---------------------------------");
                dvd.show();
                System.out.println("---------------------------------");
            }
        }
        dvds.clear();
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

    /**
     * 按名称查询
     */
    public void dvdnamequery() {

        System.out.println("请输入要查询的DVD名称:");
        Scanner scanner = new Scanner(System.in);
        dvdname = scanner.next();
        // 判断数组里是否有值
        boolean queryhave = false;
        dvds = data.dvdList();
        for (DVD dvd : dvds) {
            if (dvdname.equals(dvd.getDvdname())) {
                queryhave = true;
                System.out.println("编号\t  名称\t    状态");
                System.out.println("---------------------------------");
                dvd.show();
                System.out.println("---------------------------------");
            }
        }
        dvds.clear();
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

    /**
     * 查询DVD界面，实现查询功能
     */
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

    /**
     * 借出界面，实现借出功能
     */
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

        // 定义一个判断量
        boolean queryhave = false;
        dvds = data.dvdList();
        for (DVD dvd : dvds) {
            if (no == dvd.getId() && (dvd.getState()).equals("可以借")) {
                queryhave = true;

                System.out.println("恭喜您！此DVD在系统中的状态为：可以借！");

                String sql = "UPDATE dvd SET state=? WHERE dvdno=?";
                Object[] params = {"已借出", no};
                int ret = db.executeUpdate(sql, params);
                if (ret > 0) {
                    System.out.println("DVD已借出");
                }
            }
        }
        if (!queryhave) {
            System.out.println("对不起!操作不成功，可能因为不存在此编号或此DVD已借出!");
        }
        display();
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

    /**
     * 归还界面
     */
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

        dvds = data.dvdList();
        int num = dvds.size();
        for (DVD dvd : dvds) {
            // 判断DVD是否可以归还
            num--;
            if (no == dvd.getId()) {
                if (dvd.getState().equals("已借出")) {

                    String sql = "UPDATE dvd SET state=? WHERE dvdno=?";
                    Object[] params = {"可以借", no};
                    int ret = db.executeUpdate(sql, params);
                    if (ret > 0) {
                        System.out.println("DVD已归还");
                    }
                    break;
                } else {
                    System.out.println("DVD并未借出,请重输：");
                    revert();
                }
            } else {

                if (num < 1) {
                    System.out.println("你输入的DVD编号未找到，请重输：");
                    revert();
                } else {
                    continue;
                }

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

    /**
     * 添加DVD界面,实现添加功能
     */
    public void add() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入添加的DVD名称:");
        dvdname = scanner.next();
        String state = "可以借";

        //往数据库里插入新的DVD
        String sql = "INSERT INTO dvd(dvdname,state) VALUES(?,?)";
        Object[] params = {"《" + dvdname + "》", state};
        int rlt = db.executeUpdate(sql, params);
        if (rlt > 0) {
            System.out.println("DVD添加成功");
        }
        //调用 显示当前所有DVD信息
        display();

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

    /**
     * 删除DVD界面,实现删除功能
     */
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的DVD编号:");
        try {
            no = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("输入的不是整数，请再次输入:");
            delete();
        }
        // 判断输入的编号在数据库中是否存在
        dvds = data.dvdList();
        int length = dvds.size();
        for (DVD dvd : dvds) {
            length--;
            if (no != dvd.getId()) {
                if (length < 1) {
                    System.out.println("输入的编号不存在，请重新输入：");
                    delete();
                }
            }
        }

        String sql = "DELETE FROM dvd WHERE dvdno=? ";
        Object[] params = {no};
        int rlt = db.executeUpdate(sql, params);
        if (rlt > 0) {
            System.out.println("删除成功");
        }
        //调用 显示当前所有DVD信息
        display();

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

    /**
     * 实现修改DVD功能
     */
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
        // 从集合中查找要修改的DVD
        dvds = data.dvdList();
        for (DVD dvd : dvds) {
            if (no == dvd.getId()) {
                queryhave = true;

                System.out.println("--------正在修改的DVD信息--------");
                System.out.println("编号\t       名称\t       状态");
                dvd.show();

                System.out.println("请输入要修改的DVD名称:");
                dvdname = scanner.next();

                boolean state = true;
                do {
                    System.out.println("请输入要修改的DVD状态:");
                    dvdstate = scanner.next();

                    if ((dvdstate.equals("可以借")) || (dvdstate.equals("已借出"))) {

                        String sql = "UPDATE dvd SET dvdname=?,state=? WHERE dvdno=?";
                        Object[] params = {"《" + dvdname + "》", dvdstate, no};
                        int ret = db.executeUpdate(sql, params);
                        if (ret > 0) {
                            System.out.println("修改成功");
                        }
                        state = false;
                    } else {
                        System.out.println("修改的DVD状态不正确，请重新输入:");
                    }
                } while (state);
                // 判断输入的DVD编号是否存在
                if (!queryhave) {
                    System.out.println("当前输入的DVD编号不存在，请重新输入:");
                    update();
                }
            }
        }

        //调用 显示当前所有DVD信息
        display();

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

    /**
     * 当输入不在1-8范围或输入非法数字，系统会提示，并进行重新输入
     */
    public void reinputs() {
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
