package com.timvanx.biggerdvd.util;


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 常量类（封装了一些常用变量、方法）
 * @author TimVan
 * @date 2018年9月5日08:58:34
 * */
public class Constants {
    /**
     * VERSION  = 版本号
     * DVD_INFO_FILENAME = DVD信息txt版路径
     * ACCOUNT_FILENAME = 账户密码txt版路径
     * DVD_SER_PATH = DVD信息序列化路径
     * ACCOUNT_PATH = 账户密码序列化路径
     * DB_CONFIG_FILE = BiggerDVD的数据库配置文件
     */
    public static final String VERSION = "v0.4(数据库版)";
    public static final String DVD_INFO_FILENAME = "dvdInfo.txt";
    public static final String ACCOUNT_FILENAME = "account.txt";
    public static final String DVD_SER_PATH = "dvdInfo.ser";
    public static final String ACCOUNT_SER_PATH = "account.ser";
    public static final String DB_CONFIG_FILE
            = "BigerDvdDb.properties";

    /**
     *带异常处理的输入Int值
     */
    public static int scanfInt(){
        int inputNum = 0;

        while (true){
            try {
                Scanner scanner = new Scanner(System.in);
                inputNum = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("输入了非法数据，请进行重新输入");
                continue;
            }
            break;
        }
        return inputNum;
    }

    /**
     * 继续或返回上一步方法
     */
    public static boolean nextOrBack(){
        //退出借出循环的标识
        boolean isProgramContinue = true;

        //继续下一步或返回上一层
        System.out.println("是否继续?");
        //展示输入选项内容
        for (ContinueOption option : ContinueOption.values()) {
            System.out.println(option.getKey() + "、" + option.getValue());
        }
        int inputNum = Constants.scanfInt();
        switch (inputNum){
            //继续下一步
            case 1:
                break;
            //返回上一层
            case 0:
                isProgramContinue = false;
                break;
            default:
                System.out.println("数字不在范围内，请重新输入");
                break;
        }
        return isProgramContinue;
    }

    /**
     * 报告失败的问题
     */
    public static void reportError() {
        System.out.println("数据加载错误");
    }

    public static void reportError(String errorInfo) {
        System.out.println(errorInfo + "加载错误");
    }

    /**
     * <p>Title: clearInfoForFile</p>
     * <p>Description: 文件内容清空</p>
     *
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



