package com.antianbao.serializedvd.dvd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 安天宝
 * JAVA一班
 * 9月17日
 * <p>
 * DVD信息存入文件
 */
public class DvdDaoImpl {
    static File file = new File("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\serializedvd\\dvd\\Dvd.txt");
    private static ArrayList<Dvd> dvds;

    static {
        dvds = new ArrayList<>();
        try {
            //反序列化
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            //读数据，然后进行比对数据
            dvds = (ArrayList<Dvd>) input.readObject();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
            dvds.add(dvd);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, true));
            out.writeObject(dvds);
            //释放资源
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件 更新DVD信息
     */
    public static void writeDvd(List<Dvd> dvds) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(dvds);
            //释放资源
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件 显示DVD信息
     */
    public static List<Dvd> readDvd() {
        return dvds;
    }

}