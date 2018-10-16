package com.timvanx.biggerdvd.dvd;

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
        loadDVDInfos();
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
     * 使用数据库删除DVD信息（通过主键ID）
     * Web项目专用接口
     * @return  -1 = 找到，但未归还 , 0 = 未找到  , 1 = 删除成功
     */
    public static int deleteDVDForWeb(int id) {

        //返回的状态的识别码
        int retStatus = 0;
        loadDVDInfos();

        for (int i = 0; i < DVD.getDVDArr().size(); i++) {
            int dvdId = DVD.getDVDArr().get(i).getId();
            //是否存在
            if (dvdId == id) {
                DVD dvd = DVD.getDVDArr().get(i);
                retStatus = -1;
                //是否未被借出,未归还
                if (!dvd.isStatus()) {
                    DVD.getDVDArr().remove(i);
                    //使用数据库删除DVD信息（通过主键ID）
                    DVD.deleteDVDInfo(id);
                    System.out.println(id + "删除成功");
                    retStatus = 1;
                    break;
                }
            }
        }

        return retStatus;
    }

    /**
     * 使用数据库借出和归还DVD信息（通过主键ID）
     * Web项目专用接口
     * @return   0 = 未找到  , 1 = 删除成功
     */
    public static int loanOrReturnDVDForWeb(int id){
        //返回的状态的识别码
        int retStatus = 0;
        loadDVDInfos();

        for (int i = 0; i < DVD.getDVDArr().size(); i++) {
            int dvdId = DVD.getDVDArr().get(i).getId();
            //是否存在
            if (dvdId == id) {
                DVD dvd = DVD.getDVDArr().get(i);
                dvd.setStatus(
                        !dvd.isStatus()
                );
                //在数据中更改dvd信息
                DVD.updateDVDInfo(dvd);
                retStatus = 1;
                break;
            }
        }

        return retStatus;
    }

    /**
     * 统计DVD的数量
     * @return 返回数量
     */
    public static int countDVDs(){
        int cnt = 0;
        cnt  = JDBCUtil.count("dvd",
                "id",null);

        return cnt;
    }

    /**
     * 统计DVD已借出的数量
     * @return 返回数量
     */
    public static int countLoanedDVDs(){
        int cnt = 0;
        String tableWhere = "status = 1";
        cnt  = JDBCUtil.count("dvd",
                "id",tableWhere);
        return cnt;
    }



    /**
     * 使用数据库编辑DVD信息（通过主键ID）
     * @return boolean isExist  是否找到的标识符
     */
    public static boolean editDVDInfo(int id, String newName){

        //是否找到的标识符
        boolean isExist = false;
        loadDVDInfos();

        for (int i = 0; i < DVD.getDVDArr().size(); i++) {
            int dvdId = DVD.getDVDArr().get(i).getId();
            //是否存在
            if (dvdId == id){
                DVD dvd = (DVD) DVD.getDVDArr().get(i);
                dvd.setName(newName);
                //在数据中更改dvd信息
                DVD.updateDVDInfo(dvd);
                System.out.println("修改成功！新DVD名称为" + newName);
                isExist = true;
                break;
            }
        }

        return isExist;
    }

    /**
     * 使用数据库读入DVD信息到 DVDArr 集合
     */
    private static void loadDVDInfos() {

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

    /**
     * (带返回值)使用数据库读入DVD信息到 DVDArr 集合
     * @return 返回DVD集合
     */
    public static ArrayList<DVD> loadDVDInfosByArray() {

        ArrayList<DVD> dvds = new ArrayList<>();

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
            dvds.add(dvd);
        }

        return dvds;
    }


    @Override
    public String toString() {
        return id + "\t" + name + "\t\t" + (status ? "已借出" : "未借出");
    }

    /**
     * DVD信息转 HashMap (便于JSON格式转码)
     */
    public HashMap<String,String> toHashMap (DVD dvd){

        HashMap<String, String> dvdMap
                = new HashMap<>();
        dvdMap.put("id",String.valueOf( dvd.getId()));
        dvdMap.put("name",dvd.getName());
        dvdMap.put("status",
                dvd.isStatus()?"已借出":"未借出");
        return dvdMap;
    }
}
