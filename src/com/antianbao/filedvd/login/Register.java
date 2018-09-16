package com.antianbao.filedvd.login;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 */
public class Register {
    public  void RegisterManager(){

        //使用Scanner类定义对象
        Scanner b = new Scanner(System.in);
        System.out.println("请输入用户名");
        String user = b.nextLine();
        //使用Scanner类定义对象
        Scanner c = new Scanner(System.in);
        System.out.println("请输入密码");
        String password = c.nextLine();
        //使用Scanner类定义对象d
        Scanner d = new Scanner(System.in);
        System.out.println("请确认密码");
        String pw = d.nextLine();

        if( password.equals(pw) ){
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filedvd\\login\\User", true));
                bw.write(user + "," + password + "\r\n");
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("两次密码不一致，请重新输入。");
            RegisterManager();
        }

    }
}
