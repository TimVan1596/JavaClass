package com.antianbao.text;

import java.io.*;

/**
 * 用字节流实现图片的复制
 *
 * @author 安天宝
 * @date 2018/9/21
 */
public class CopyPicture {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("D:\\touxiang.jpg");
            FileOutputStream fos = new FileOutputStream("E:\\touxiangCopy.jpg");
            int n;
            byte[] bytes = new byte[1024];
            while ((n = (fis.read(bytes))) != -1) {
                fos.write(bytes, 0, n);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("要复制的图片不存在，路径为：'D:\\touxiang.jpg'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
