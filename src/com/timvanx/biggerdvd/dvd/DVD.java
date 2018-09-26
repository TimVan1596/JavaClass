package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.Constants;
import com.timvanx.biggerdvd.util.JDBCUtil;

import java.io.*;
import java.util.ArrayList;

import static com.timvanx.biggerdvd.util.Constants.DVD_SER_PATH;

/**
 * DVD类
 *
 * @author TimVan
 * @date 2018年9月6日09:16:30
 */
public class DVD implements Serializable {
    /**
     * serialVersionUID = 版本一致性
     * cnt = 随着构造函数自增(ID赋值)
     * status = 借出状态，未借出为false，反之亦然
     * name = DVD名称
     * id = （主键自增）DVD编号
     */
    private static final long serialVersionUID = 1L;
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
        serializeLoadDVDInfosToFile();
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
     * 使用序列化将DVD信息存入文件Constants.DVD_SER_PATH
     */
    public static void serializeSaveDVDInfosToFile() {
        //DVD信息序列化路径
        File file = new File(DVD_SER_PATH);

        try {
            //检查文件是否存在，不存在则创建
            if (!file.exists()) {
                //创建文件
                boolean ret = file.createNewFile();
            }
            FileOutputStream fileOut =
                    new FileOutputStream(DVD_SER_PATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(DVDArr);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            Constants.reportError("序列化DVD信息文件");
            i.printStackTrace();
        }

    }

    /**
     * 将DVD信息存入文件Constants.DVD_INFO_FILENAME
     */
    public static void clearAndSaveDVDInfosToFile() {
        //先清除Constants.DVD_INFO_FILENAME中所有DVD数据
        Constants.clearInfoForFile(Constants.DVD_INFO_FILENAME);
        //逐项写入文件
//        for (DVD dvd : DVDArr) {
//            saveDVDInfosToFile(dvd.getId(), dvd.getName(), dvd.isStatus());
//        }
        serializeSaveDVDInfosToFile();

    }

    /**
     * 使用数据库从配置文件Constants.DB_CONFIG_FILE读入到DvdArr集合
     */
    public static void serializeLoadDVDInfosToFile() {

        JDBCUtil.setConfigFile(Constants.DB_CONFIG_FILE);


    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t\t" + (status ? "已借出" : "未借出");
    }
}
