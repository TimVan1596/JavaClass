package com.zhangmin.com.aiit.homework;

import java.util.Scanner;

    /*定义一个类CalF，完成加、减方法，然
    后再定义一个派生类CalS，完成乘、除方
    法*/

    public class CalF {
        public static int a;
        public static int b;
        static Scanner scanner = new Scanner(System.in);
        public static void sumNum(int a,int b){
            int sum;
            sum = a+b;
            System.out.println("您输入的两个数之和为"+sum);
        }
        public static void subNum(int a,int b){
            int sub;
            sub = a-b;
            System.out.println("您输入的两个数之差为"+sub);
        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            System.out.println("请输入第一个数：");

            int  x= scanner.nextInt();
            a=x;
            System.out.println("请输入第二个数：");
            int  y = scanner.nextInt();
            b = y;
            sumNum(x,y);
            subNum(x,y);
            CalS num = new CalS();
            num.mulNum(x,y);
            num.divNum(x, y);

        }

    }

