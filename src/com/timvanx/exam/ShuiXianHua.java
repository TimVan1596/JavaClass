package com.timvanx.exam;

public class ShuiXianHua {

    public static void main(String[] args) {
        new ShuiXianHua().printFlower();
//        System.out.println(ge+"-"+shi+"-"+bai);
    }

    public void printFlower() {
        for (int i = 100; i < 1000; i++) {
            int ge = i % 10;
            int shi = ((i % 100) - ge) / 10;
            int bai = (i - ge - 10 * shi) / 100;


            if ((ge * ge * ge + shi * shi * shi + bai * bai * bai) == i) {
                System.out.println(i);
            }

        }
    }
}
