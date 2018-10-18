package com.antianbao.text;
/**
 * 练习
 *
 * @author 安天宝
 * @date 2018/9/14
 */

import java.io.*;

public class StudentFile {
    public static void main(String[] args) {
        String path = FileReader.class.getResource("/").getFile();
        String fileName = path + "/student.data";
        String info = StudentFile.readStudentInfo(fileName);
        System.out.println(info);

        String[] infos = {"Lucy,女,15", "Lilei,男,16"};
        StudentFile.writeStr2File(fileName, infos);
        info = StudentFile.readStudentInfo(fileName);
        System.out.println(info);
    }

    public static void writeStr2File(String fileName, String[] infos) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            for(String info:infos){
                byte[] b = info.getBytes();
                fos.write(b);
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readStudentInfo(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            String content = new String(bytes);
            fis.close();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}