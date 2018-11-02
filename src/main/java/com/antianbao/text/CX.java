package com.antianbao.text;

import java.io.*;

public class CX {
    public static void main(String[] args) throws IOException {
        //参数为空
        File directory = new File("");
        //当前目录的datademo文件下
        String courseFile = directory.getCanonicalPath()+"\\"+"datademo";
        BufferedWriter bw = new BufferedWriter(new FileWriter(courseFile, true));
        //拼接字符串
        int a = 6;
        double b = 6.6;
        String c = "123";
        String info =  a + "," + b + "," + c;
        //写进去
        bw.write(info);
        //换行
        bw.newLine();
        //刷新
        bw.flush();
        //释放资源
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(courseFile));
        //读数据，然后进行比对数据
        String line;
        while ((line = br.readLine()) != null) {
            String[] strings = line.split(",");
            System.out.println(strings[0]);
            System.out.println(strings[1]);
            System.out.println(strings[2]);
        }
        br.close();
    }
}
