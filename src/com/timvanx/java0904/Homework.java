package com.timvanx.java0904;

import java.util.Random;
import java.util.Scanner;

/**
 * 2018年9月4日 Java班作业
 */
public class Homework {
    //求取偶数和
    private int sumEven(int start, int end) {
        if (end < start) {
            int temp = start;
            start = end;
            end = temp;
        }

        int sum = 0;
        for (int i = start; i < end; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    //判断年份是否为闰年
    private boolean isRunYear(int year) {

        boolean isRun = false;
        //四年闰，百年不闰，四百年再闰
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            isRun = true;
        }
        return isRun;
    }

    //打印水仙花数
    private void printFlowerNum(int start, int end) {
        if (end < start) {
            int temp = start;
            start = end;
            end = temp;
        }

        if (end > 999) {
            System.out.println("超过三位数！");
            return;
        }
        else if (start < 100){
            System.out.println("低于三位数！");
            return;
        }


        int count = 0;

        for (int i = start; i < end; i++) {
            int a = i%10;
            int b = ((i-a) / 10) %10;
            int c = (i-b-a) / 100;

            if (a*a*a + b*b*b + c*c*c == i) {
                System.out.println(i);
                count++;
            }
        }

        if (count == 0){
            System.out.println("未找到水仙花数！");
        }

    }

    //猜数字游戏
    private void guessNum(final int MIN,final int MAX){
        Random random = new Random();
        //获取随机数，猜数范围
        int answer = (random.nextInt(MAX) % (MAX-MIN+1))+MIN;
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num != answer){
            if (num < answer){
                System.out.println("小了！");
            }else {
                System.out.println("大了！");
            }
            System.out.print("重新输入：");
            num = scanner.nextInt();
        }
        System.out.println("恭喜你答对了！");
    }

    //用户登录验证
    private boolean login(final String userName,final String userPassword){
        //设置默认用户名和密码
        final String realName = "admin";
        final String realPassword = "admin";

        boolean isLogin = false;
        if (realName.equals(userName) && realPassword.equals(userPassword)){
            isLogin = true;
        }
        return isLogin;
    }


    public static void main(String... args) {
        Homework work = new Homework();
        Employee emp = new Employee();
        emp.setId(123);

        //3.1 - 求1到100的偶数和
//        System.out.println("1、求其1到100的偶数和 ："+ work.sumEven(1,100));


        //3.2、3.3 - 判断一个年份是否为闰年
//        System.out.println("2、判断一个年份是否为闰年，请输入年份");
//        Scanner scanner = new Scanner(System.in);
//        if (work.isRunYear(scanner.nextInt())){
//            System.out.println("是闰年");
//        }
//        else{
//            System.out.println("不是闰年");
//        }


        //3.4 - 统计1到1000的水仙花数(低于100不存在水仙花数)
//        work.printFlowerNum(100,999);


        //3.5、3.6 - 猜数字游戏(1~9)
//        System.out.println("猜数字小游戏，请输入一个数，范围1到9");
//        work.guessNum(1,9);

        //3.7 模拟用户登录，尝试超过三次程序终止
        System.out.println("模拟用户登录，尝试超过三次程序终止");
        int loginCNT = 0;
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名");
            String userName = scanner.nextLine();
            System.out.println("请输入密码");
            String userPassword = scanner.nextLine();
            if (work.login(userName,userPassword)){
                System.out.println("登录成功！");
                break;
            }
            else {
                System.out.println("登录失败！");
            }
            loginCNT++;
        }
        if (loginCNT == 3){
            System.out.println("尝试次数过多，终止程序！");
        }




    }
}
