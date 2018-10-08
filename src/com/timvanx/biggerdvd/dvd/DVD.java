package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.Constants;
import com.timvanx.biggerdvd.util.JDBCUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        loadDVDInfos();
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
     * 使用数据库增加DVD信息
     */
    public static void addDVDInfo(DVD newDVD) {
        //boolean型的newDVD.isStatus()转数字0、1
        int newDVDStatus = 0;
        if (newDVD.isStatus()) {
            newDVDStatus = 1;
        }

        Map<String, Object> insertData =
                new HashMap<String, Object>(1);
        insertData.put("name", newDVD.getName());
        insertData.put("status", newDVDStatus);
        JDBCUtil.insert("dvd", insertData);
    }


    /**
     * 使用数据库修改DVD信息
     */
    public static void updateDVDInfo(DVD newDVD) {
        //boolean型的newDVD.isStatus()转数字0、1
        int newDVDStatus = 0;
        if (newDVD.isStatus()) {
            newDVDStatus = 1;
        }

        //在数据库中update DVD的名称和状态
        Map<String, Object> updateData =
                new HashMap<>(1);
        updateData.put("name", newDVD.getName());
        updateData.put("status", newDVDStatus);
        String tableWhere = " id = " + newDVD.getId();
        JDBCUtil.update("dvd", updateData
                , tableWhere);
    }

    /**
     * 使用数据库删除DVD信息（通过主键ID）
     */
    public static void deleteDVDInfo(int id) {
        String tableWhere = " id = " + id;
        JDBCUtil.delete("dvd", tableWhere);
    }

    /**
     * 使用数据库读入DVD信息到 DVDArr 集合
     */
    public static void loadDVDInfos() {

        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("id");
            add("name");
            add("status");
        }};
        List<List<String>> dvdSQLs = JDBCUtil
                .select("dvd",
                        tableField, null,
                        null, null);

        for (List<String> dvdInfo : dvdSQLs) {
            //将字符串转为3种数据
            int id = Integer.valueOf(dvdInfo.get(0));
            String name = dvdInfo.get(1);
            boolean status = true;
            if (dvdInfo.get(2).equals("0")) {
                status = false;
            }
            DVD dvd = new DVD(id, name, status);
            DVDArr.add(dvd);
        }

    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t\t" + (status ? "已借出" : "未借出");
    }
}
