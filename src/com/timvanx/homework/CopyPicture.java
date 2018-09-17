package com.timvanx.homework;

import java.io.*;

/**
 * 复制图片到E盘
 */
public class CopyPicture {
    private final String SRC_FILENAME = "123.png";
    private final String DST_FILENAME = "C:\\res\\newPic.jpg";

    public static void main(String[] args) {
        CopyPicture copyPicture = new CopyPicture();
        copyPicture.init();

    }

    private void init() {
        //srcFile 原文件
        File srcFile = new File(SRC_FILENAME);
        File dstFile = new File(DST_FILENAME);


        //判断文件夹是否存在
        if (!dstFile.getParentFile().exists()) {
            if (!dstFile.getParentFile().mkdirs()) {
                System.out.println("目标文件夹创建失败");
            } else {
                try {
                    dstFile.createNewFile();
                } catch (IOException e) {
                    System.out.println("目标文件创建失败");
                    e.printStackTrace();
                }
            }

        } else {
            try {
                dstFile.createNewFile();
            } catch (IOException e) {
                System.out.println("目标文件创建失败");
                e.printStackTrace();
            }
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
            byte[] readBuffer = new byte[1024];
            int hasRead = 0;

            //边读边写
            while ((hasRead = fileInputStream.read(readBuffer)) > 0) {
                fileOutputStream.write(readBuffer, 0, hasRead);
            }

            fileInputStream.close();
            fileOutputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
