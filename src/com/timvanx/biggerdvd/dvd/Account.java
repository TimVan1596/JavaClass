package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.Constants;

import java.io.*;
import java.util.ArrayList;

/**
 * 账户类
 * @author TimVan
 * @date 2018年9月5日09:33:27
 * */
public class Account implements Serializable {
    private  String usrName;
    private  String usrPassword;

    /**
     * serialVersionUID = 版本一致性
     * accountArrayList  = 保存所有的账户-密码
     * loginCNT = 保存用户尝试登陆的次数
     */
    private static final long serialVersionUID = 1L;
    private static ArrayList<Account> accountArrayList;
    private static int loginCNT;

    static {
        accountArrayList = new ArrayList<>();
        loginCNT = 0;
    }

    private Account(String usrName,String usrPassword) {
        this.usrName = usrName;
        this.usrPassword = usrPassword;
    }

    public Account() {
        //从文件中读取所有的账户-密码信息
        serializeLoadAccountToFile();
    }

    /**
     * 登录
     *@return boolean 是否登录成功
     */
    public static boolean login(String name, String password) {
        boolean isLogin = false;

        //判断账户和密码是否存在和正确
        for (Account account:accountArrayList){
            if (account.getUsrName().equals(name)){
                if (account.getUsrPassword().equals(password)){
                    System.out.println("登录成功！");
                    isLogin = true;
                }
                break;
            }
        }

        if (!isLogin){
            System.out.println("登录失败！请检查用户名或密码是否输入错误");
        }

        return isLogin;
    }

    /**
     * 注册
     * @return boolean 是否注册成功
     */
    public static boolean register(String name, String password) {
        //检查用户名是否已存在
        boolean isNameIsexist = isAccountExist(name);

        //若用户名不存在创建用户
        if (!isNameIsexist){
            accountArrayList.add(new Account(name,password));
            //更新所有账户数据
            clearAndSaveAccountToFile();
        }

        return !isNameIsexist;
    }

    public static int getLoginCNT() {
        return loginCNT;
    }

    public static void setLoginCNT(int loginCNT) {
        Account.loginCNT = loginCNT;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }


    /**
     * 将DVD信息存入文件Constants.ACCOUNT_SER_PATH
     */
    public static void clearAndSaveAccountToFile() {
        //先清除Constants.ACCOUNT_SER_PATH文件
        Constants.clearInfoForFile(Constants.ACCOUNT_SER_PATH);
        //写入文件
        serializeSaveAccountToFile();

    }

    /**
     * 使用序列化将账户存入文件Constants.ACCOUNT_SER_PATH
     */
    public static void serializeSaveAccountToFile() {
        //DVD信息序列化路径
        File file = new File(Constants.ACCOUNT_SER_PATH);

        try {
            //检查文件是否存在，不存在则创建
            if (!file.exists()) {
                //创建
                file.createNewFile();
            }
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(accountArrayList);
            fileOut.close();
            out.close();

        } catch (IOException i) {
            Constants.reportError("序列化账户信息");
            i.printStackTrace();
        }

    }

    /**
     * 通过用户名检查该账户是否存在
     */
    public static boolean isAccountExist(String name) {
        boolean isNameIsexist = false;
        //判断账户名是否存在和正确
        for (Account account : accountArrayList) {
            if (account.getUsrName().equals(name)) {
                isNameIsexist = true;
                break;
            }
        }
        return isNameIsexist;
    }

    /**
     * 使用序列化从文件Constants.ACCOUNT_SER_PATH读入到accountArrayList集合
     */
    public static void serializeLoadAccountToFile() {

        //DVD信息序列化路径
        File file = new File(Constants.ACCOUNT_SER_PATH);

        try {
            //检查文件是否存在
            if (file.exists()) {
                FileInputStream fileIn =
                        new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                accountArrayList = (ArrayList<Account>) in.readObject();
                in.close();
                fileIn.close();
            }

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return;


    }

    /**
     * 通过用户名修改密码
     */
    public static boolean changePasswordByName(String name, String password) {
        boolean isAccountExist = false;

        //判断账户名是否存在
        if (isAccountExist(name)) {

            for (int i = 0; i < accountArrayList.size(); i++) {
                Account account = (Account) accountArrayList.get(i);
                if (account.getUsrName().equals(name)) {
                    //修改密码
                    account.setUsrPassword(password);
                    isAccountExist = true;
                    //更新所有账户数据
                    clearAndSaveAccountToFile();
                    break;
                }
            }

        }

        return isAccountExist;
    }
}
