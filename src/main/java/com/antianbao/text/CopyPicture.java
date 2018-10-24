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
            FileInputStream fis = new FileInputStream("E:\\JAVA\\java_direction_class\\out\\artifacts\\java_direction_class_war_exploded\\\\upload\\20181023220142png.png");
            FileOutputStream fos = new FileOutputStream("E:\\JAVA\\java_direction_class\\web\\atb\\javaWebDvd\\image\\"+"JD.png");
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
