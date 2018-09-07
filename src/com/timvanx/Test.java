package com.timvanx;

import java.math.BigInteger;

/**
 * 求取斐波那契数列的第一百个数
 * */
public class Test {

    public static void main(String[] args) {

        //防止溢出采用大整数类
        BigInteger a =BigInteger.valueOf(0);
        BigInteger b =BigInteger.valueOf(1);
        BigInteger temp;

        for (int i = 0; i < 100; i++) {
            temp = a.add(b);
            a = b;
            b = temp;
//            if (i == 99)
            System.out.println((i+1)+ "："+temp);
        }

    }
}