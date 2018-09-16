package com.antianbao.filedvd.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 * <p>
 * 注册写入文件 登陆读取文件
 */
public class UserDaoImpl {

    static File file = new File("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filedvd\\login\\User");

    //静态代码块，随着类的加载而加载
    static {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册 写入文件
     */
    public boolean regist(User user) {
        boolean flag = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //读数据，然后进行比对数据
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split(",");
                if (strings[0].equals(user.getName())) {
                    return flag;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            //拼接字符串
            String info = user.getName() + "," + user.getPassword();
            //写进去
            bw.write(info);
            //换行
            bw.newLine();
            //刷新
            bw.flush();
            //释放资源
            bw.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 登陆 读取文件
     */
    public boolean isLogin(String name, String password) {
        boolean flag = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //读数据，然后进行比对数据
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split(",");
                if (name.equals(strings[0]) && password.equals(strings[1])) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
