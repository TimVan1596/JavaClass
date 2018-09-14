package com.antianbao.task914;

import java.util.Scanner;

/**
 * 9/14 平台题目
 *
 * @author 安天宝
 * @date 2018/9/14
 */
public class Main {
    public static  void main(String[] args){
        System.out.println("请输入第一个数");
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();

        System.out.println("请输入第二个数");
        Scanner s1 = new Scanner(System.in);
        int b = s1.nextInt();

        CalS cs = new CalS();
        System.out.print("a + b = ");
        cs.add(a,b);
        System.out.print("a - b = ");
        cs.subtract(a,b);
        System.out.print("a * b = ");
        cs.multiply(a,b);
        System.out.print("a / b = ");
        cs.divide(a,b);

    }
}

class CalF {

    public void add(int a,int b){
        System.out.println( a + b );
    }

    public void subtract(int a,int b){
        System.out.println( a - b );
    }

}

class CalS extends CalF{

    public void multiply(int a,int b){
        System.out.println( a * b );
    }

    public void divide(int a,int b){
        System.out.println( a / b );
    }

}

