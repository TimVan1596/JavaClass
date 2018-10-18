package com.zhangmin.com.aiit.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedTest {


    public static void main(String[] args){
        try {
            FileReader fr = new FileReader("E:\\就这样吧\\假如生活欺骗了你.txt");
            BufferedReader br = new BufferedReader(fr);
            int count = 0;//记录输出多少行
            String line = null;
            while ((line = br.readLine()) != null) {
                count++;
            }
            fr.close();
            br.close();
            System.out.println(count);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
