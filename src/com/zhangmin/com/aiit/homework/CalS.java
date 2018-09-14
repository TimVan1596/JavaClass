package com.zhangmin.com.aiit.homework;

public class CalS extends CalF{
    public  void mulNum(int a,int b){
        int mul;
        mul = a*b;
        System.out.println("您输入的两个数之积为"+mul);
    }
    public  void divNum(int a,int b){
        int div;
        if(b==0){
            System.out.println("您输入的除数为0，错误！！！");
        }else{
            div = a/b;
            System.out.println("您输入的两个数之商为"+div);
        }
    }
}