package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

import static com.timvanx.biggerdvd.util.Constants.reportError;

/**
 * DVD类
 *
 * @author TimVan
 * @date 2018年9月6日09:16:30
 */
public class DVD {
    /**
     * cnt = 随着构造函数自增(ID赋值)
     * status = 借出状态，未借出为false，反之亦然
     */
    private static int cnt = 1000;
    private int id;
    private String name;
    private boolean status;

    public static ArrayList<DVD> getDVDArr() {
        return DVDArr;
    }

    /**
     * Static DVDArr = DVD集合信息
     */
    private static ArrayList<DVD> DVDArr;

    static {
        DVDArr = new ArrayList<>();
        //编号从1000开始初始化
        DVD.setCnt(1000);
        //初始化DVD数组
        loadDVDInfosToFile();
    }

    private DVD(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
        //读取时读入主键
        cnt = id;
    }


    public DVD() {
    }

    public DVD(String name) {
        //随着构造函数自增(ID赋值)
        this.id = ++cnt;
        this.name = name;
        //默认为未借出
        this.status = false;
    }

    public static int getCnt() {
        return cnt;
    }

    public static void setCnt(int cnt) {
        DVD.cnt = cnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    /**
     * 将DVD信息存入文件Constants.DVD_INFO_FILENAME
     */
    public static void saveDVDInfosToFile(int id, String name
            , boolean status) {
        File file = new File(Constants.DVD_INFO_FILENAME);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //格式化信息

            String DVDInfosStrings = new StringBuffer().append(id).append("-").append(name).append("-").append((status ? "已借出" : "未借出")).append("\r\n").toString();
            bufferedWriter.write(DVDInfosStrings);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 将DVD信息存入文件Constants.DVD_INFO_FILENAME
     */
    public static void clearAndSaveDVDInfosToFile() {
        //先清除Constants.DVD_INFO_FILENAME中所有DVD数据
        Constants.clearInfoForFile(Constants.DVD_INFO_FILENAME);
        //逐项写入文件
        for (DVD dvd : DVDArr) {
            saveDVDInfosToFile(dvd.getId(), dvd.getName(), dvd.isStatus());
        }

    }

    /**
     * 从文件Constants.DVD_INFO_FILENAME读入到DvdArr集合
     */
    private static void loadDVDInfosToFile() {
        File file = new File(Constants.DVD_INFO_FILENAME);

        //账号文件"Constants.ACCOUNT_FILENAME"是否存在，存在则读入
        if (file.exists()) {

            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                //循环读取文件中的所有DVD信息（-分割）
                String accountLine;
                while ((accountLine = bufferedReader.readLine()) != null) {
                    String[] DVDLineStrings = accountLine.split("-");
                    //转换id字符串
                    int id = 0;
                    try {
                        id = Integer.valueOf(DVDLineStrings[0]);
                    } catch (InputMismatchException exception) {
                        id = 0;
                        reportError("DVD编号");
                        exception.printStackTrace();
                    }
                    //转换status(借出信息)字符串
                    boolean status = false;
                    if (DVDLineStrings[2].equals("已借出")) {
                        status = true;
                    }

                    DVDArr.add(new DVD(id, DVDLineStrings[1], status));
                }

            } catch (FileNotFoundException e) {
                reportError("账户文件");
                e.printStackTrace();
            } catch (IOException e) {
                reportError("读取数据");
                e.printStackTrace();
            }

        }


    }


    @Override
    public String toString() {
        return id + "\t" + name + "\t\t" + (status ? "已借出" : "未借出");
    }
}
