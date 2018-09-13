package com.timvanx.biggerdvd.dvd;

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
    }
    private int id;
    private String name;
    private boolean status;

    public DVD() {
    }

    public DVD(String name) {
        //随着构造函数自增(ID赋值)
        this.id = cnt++;
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
     * 将DVD信息存入文件"dvdInfo.txt"
     */
    public static void saveDVDInfosToFile(String id, String name
            , boolean status) {
        File file = new File("dvdInfo.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //格式化信息
            String DVDInfosStrings =
                    id + "-" + name + "-"
                            + (status ? "已借出" : "未借出" + "\r\n");
            bufferedWriter.write(DVDInfosStrings);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 从文件"dvdInfo.txt"读入到DvdArr集合
     */
    private static void loadDVDInfosToFile() {
        File file = new File("dvdInfo.txt");

        //账号文件"account.txt"是否存在，存在则读入
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
