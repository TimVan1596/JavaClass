package com.smallfangyu.pictureinout;

import java.io.*;
public class Picture {
    public static void main(String[] args){

        byte[] buffer=new byte[1024];
        int hasRead=0;
        try {
            FileInputStream fis = new FileInputStream("D:\\timg.jpg");
            FileOutputStream fos=new FileOutputStream("F:\\mao.jpg");

            while((hasRead=fis.read(buffer))>0){
                fos.write(buffer);
            }
           fis.close();
           fos.close();
          }catch(Exception e){
            e.printStackTrace();
        }
    }
}
