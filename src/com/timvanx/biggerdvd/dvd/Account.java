package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.Constants;

import java.io.*;
import java.util.ArrayList;

/**
 * 账户类
 * @author TimVan
 * @date 2018年9月5日09:33:27
 * */
public class Account {
    private  String usrName;
    private  String usrPassword;

    /**
     * accounts 保存所有的账户，静态
     */
    private static ArrayList<Account> accountArrayList;

    private Account(String usrName,String usrPassword) {
        this.usrName = usrName;
        this.usrPassword = usrPassword;
    }

    public Account() {
        accountArrayList = new ArrayList<>();
        loadAccountFromFile();
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
        boolean isNameIsexist = false;
        //判断账户名是否存在和正确
        for (Account account:accountArrayList){
            if (account.getUsrName().equals(name)){
                isNameIsexist = true;
                break;
            }
        }

        //若用户名不存在创建用户
        if (!isNameIsexist){
            accountArrayList.add(new Account(name,password));
            saveAccountToFile(name, password);
        }

        return !isNameIsexist;
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
     * 将账户存入文件Constants.ACCOUNT_FILENAME
     */
    private static void saveAccountToFile(String name, String password) {
        File file = new File(Constants.ACCOUNT_FILENAME);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(name + "-" + password + "\r\n");
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 从文件Constants.ACCOUNT_FILENAME读入到账户数组
     */
    private static void loadAccountFromFile() {
        File file = new File(Constants.ACCOUNT_FILENAME);

        //账号文件Constants.ACCOUNT_FILENAME是否存在，存在则读入
        if (file.exists()) {

            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                //循环读取文件中的所有账户
                String accountLine;
                while ((accountLine = bufferedReader.readLine()) != null) {
                    String[] accountLineStrings = accountLine.split("-");
                    accountArrayList.add(new Account(accountLineStrings[0]
                            , accountLineStrings[1]));
                }

            } catch (FileNotFoundException e) {
                Constants.reportError("账户文件");
                e.printStackTrace();
            } catch (IOException e) {
                Constants.reportError("读取数据");
                e.printStackTrace();
            }

        }


    }

}
