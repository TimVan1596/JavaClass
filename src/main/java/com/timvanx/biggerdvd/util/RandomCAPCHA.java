package com.timvanx.biggerdvd.util;

import java.util.Random;
/**
 * 随机生成5位大小写字母或者数字
 * @author TimVan
 * @date 2018年11月12日16:13:30
 * */
public class RandomCAPCHA {

    /**
     * 生成重复的
     * @return 随机生成5位大小写字母或者数字
     * */
    public static String createRandomCAPCHA() {
        String str = "";
        Random rand = new Random();
        for(int i=0;i<5;i++){
            int num = rand.nextInt(2);
            switch(num){
                case 0:
                    //生成随机大写字母
                    char c2 = (char)(rand.nextInt(26)+'A');
                    str += c2;
                    break;
                case 1:
                    //生成随机数字
                    str += rand.nextInt(10);
                    break;
                default:
            }
        }
        return str;
    }

}
