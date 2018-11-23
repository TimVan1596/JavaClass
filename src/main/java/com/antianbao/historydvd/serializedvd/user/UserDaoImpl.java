package com.antianbao.historydvd.serializedvd.user;

import java.io.*;
import java.util.ArrayList;

/**
 * @author 安天宝
 * JAVA一班
 * 9月20日
 * <p>
 * 注册写入文件 登陆读取文件
 */
public class UserDaoImpl {
    static File file = new File("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\serializedvd\\user\\User.txt");
    private static ArrayList<User> users;
    static {
        users = new ArrayList<>();
        try {
            //反序列化
            loadAccountFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //静态代码块，随着类的加载而加载
    static {
        if (!file.exists()) {
            //创建文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 注册 写入文件
     */
    public boolean regist(String name, String password) {
        boolean flag = false;
        try {
            for(User user:users){
                if(name.equals(user.getName())){
                    return flag;
                }
            }
            users.add(new User(name, password));
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(users);
            //释放资源
            out.close();
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
            for(User user:users){
                if(name.equals(user.getName()) && password.equals(user.getPassword())){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    private static void loadAccountFile() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
        //读数据，然后进行比对数据
        users = (ArrayList<User>) input.readObject();
        input.close();
    }

}
