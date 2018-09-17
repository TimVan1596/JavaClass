package com.antianbao.filedvd.dvd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author 安天宝
 * JAVA一班
 * 9月17日
 * <p>
 * DVD信息存入文件
 */
public class DvdDaoImpl {
    static File file = new File("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filedvd\\dvd\\Dvd");
    //静态代码块，随着类的加载而加载，如果没有就创建
    static {
        try {
            if (!file.exists()) {
                System.out.println("不存在");
                System.out.println(file.createNewFile());
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 写入文件 添加DVD
     */
    public static void addDvd(Dvd dvd) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            //写入
            bw.write(dvd.getNo() + "\t" + dvd.getName() + "\t" + dvd.getState() + "\r\n");
            //刷新
            bw.flush();
            //释放资源
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 写入文件 更新DVD信息
     */
    public static void writeDvd(List<Dvd> dvds) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Dvd dvd : dvds) {
                //写入
                bw.write(dvd.getNo() + "\t" + dvd.getName() + "\t" + dvd.getState() + "\r\n");
                //刷新
                bw.flush();
            }
            //释放资源
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取文件 显示DVD信息
     */
    public static List<Dvd> readDvd() {
        ArrayList<Dvd> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ( (line = br.readLine()) != null ) {
                String[] info = line.split("\t");
                list.add(new Dvd(Integer.valueOf(info[0]), info[1], info[2]));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}