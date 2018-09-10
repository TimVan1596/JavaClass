package com.timvanx.biggerdvd.util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 常量类（封装了一些常用变量、方法）
 * @author TimVan
 * @date 2018年9月5日08:58:34
 * */
public class Constants {
    /**
     *VERSION  = 版本号
     */
    public static final String VERSION = "v0.2(集合版)";

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

}



