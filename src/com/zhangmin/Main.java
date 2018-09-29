package com.zhangmin;

import java.util.ArrayList;

public class Main {
    public Main(){}
    static void print(ArrayList al){
        al.add(2);
        al=new ArrayList();
        al.add(3);
        al.add(4);
    }
    public static void main(String[]args){
        Main test=new Main();
        ArrayList al=new ArrayList();
        al.add(1);
        print(al);
        System.out.println(al.get(1));
    }
}
