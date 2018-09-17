package com.zhangmin.com.aiit.iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class IOStream {
    public static void main(String[] args)throws Exception{
        FileInputStream file = new FileInputStream("D:\\文件传输测试\\H4ZT0GX{WRJMFD)3{XI9{G0.png");
        FileOutputStream file2 = new FileOutputStream("E:\\文件拷贝\\123.png");
        int n;
        while((n = file.read()) != -1){
            file2.write((char)n);
        }
        System.out.println("复制成功！");
        file.close();
        file2.close();
    }
}
