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
 * @date 2018年10月18日09:47:57
 */
public class DVD implements Serializable {
    /**
     * p.s. 使用 Alibaba fastJson 传输对象时需要 JavaBean 标准
     * serialVersionUID = 版本一致性
     * status = 借出状态，未借出为false，反之亦然
     * name = DVD名称
     * id = （主键自增）DVD编号
     * preview = 预览图URL
     */
    private static final long serialVersionUID = 2L;
    private int id;
    private String name;
    private boolean status;
    private String preview;

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

        //初始化DVD数组
        loadDVDInfos();
    }

    private DVD(int id, String name
            , boolean status , String preview) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.preview = preview;
    }


    public int getId() {
        return id;
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
    public static void addDVDInfo(String name,String preview) {

        Map<String, Object> insertData =
                new HashMap<String, Object>(1);
        insertData.put("name", name);
        insertData.put("preview", preview);
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
     * 通过ID，使用数据库修改DVD信息
     */
    public static void updateDVDInfo(int id, String newName
            ,String preview) {

        //在数据库中update DVD的名称和状态
        Map<String, Object> updateData =
                new HashMap<>(1);
        updateData.put("name", newName);
        updateData.put("preview", preview);
        String tableWhere = " id = " + id;
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
     * 使用数据库将DVD信息从回收站还原（通过主键ID）
     * @param query 批量还原条件
     */
    public static void returnDVDInfoFromBin(String query) {
        //在数据库中修改DVD的hidden为true
        Map<String, Object> updateData =
                new HashMap<>(1);
        updateData.put("hidden", "0");
        String tableWhere = " id  in ( " + query + " ) ";
        JDBCUtil.update("dvd", updateData
                , tableWhere);
    }

    /**
     * 使用数据库将DVD信息移入回收站（通过主键ID）
     * @param query 批量删除条件
     */
    private static void deleteDVDInfo(String query) {
        //在数据库中修改DVD的hidden为true
        Map<String, Object> updateData =
                new HashMap<>(1);
        updateData.put("hidden", "1");
        String tableWhere = " id  in ( " + query + " ) ";
        JDBCUtil.update("dvd", updateData
                , tableWhere);
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
                }
                break;
            }
        }

        return retStatus;
    }

    /**
     * 使用数据库删除DVD信息（通过主键ID）
     * Web项目专用接口
     * @param query  删除条件
     */
    public static void deleteDVDForWeb(String query){
        //使用数据库删除DVD信息（通过主键ID）
        DVD.deleteDVDInfo(query);
    }

    /**
     * 使用数据库借出和归还DVD信息（通过主键ID）
     * Web项目专用接口
     * @return   0 = 未找到  , 1 = 编辑成功
     */
    public static int loanOrReturnDVDForWeb(int id){


        loadDVDInfos();
        //返回的状态的识别码
        int retStatus = 0;

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

        System.out.println("退出 loanOrReturnDVDForWeb");
        return retStatus;
    }

    /**
     * 统计DVD的数量
     * @return 返回数量
     */
    public static int countDVDs(){
       return countDVDs(null);
    }

    /**
     * 统计DVD的数量
     * @return 返回数量
     */
    public static int countDVDs(boolean isHidden,String tableWhere){
        String tableName = "showDVD";
        if (isHidden){
            tableName = "hiddenDVD";
        }

        int cnt = 0;
        cnt  = JDBCUtil.count(tableName,
                "id",tableWhere);

        return cnt;
    }

    /**
     *(不显示回收站）
     * @return 返回数量
     */
    public static int countDVDs(String tableWhere){
        return countDVDs(false,tableWhere);
    }

    /**
     * 统计DVD已借出的数量
     * @return 返回数量
     */
    public static int countLoanedDVDs(){
        int cnt = 0;
        String tableWhere = "status = 1";
        cnt  = JDBCUtil.count("showdvd",
                "id",tableWhere);
        return cnt;
    }

    /**
     * 使用数据库编辑DVD信息（通过主键ID）
     * @return boolean isExist  是否找到的标识符
     */
    public static boolean editDVDInfo(int id, String newName
            ,String preview){

            DVD.updateDVDInfo(id,newName,preview);

        return true;
    }

    //
    public static boolean editDVDInfo(int id, String newName){
            String preview = "https://cloud.timvanx.com/timg.jpg";
            return editDVDInfo(id,newName,preview);
    }

    /**
     * 使用数据库读入DVD信息到 DVDArr 集合
     */
    private static void loadDVDInfos() {


        List<List<String>> dvdSQLs =
                getDVDInfosFromJDBC();

        for (List<String> dvdInfo : dvdSQLs) {
            //将字符串转为3种数据
            int id = Integer.valueOf(dvdInfo.get(0));
            String name = dvdInfo.get(1);
            boolean status = true;
            if (dvdInfo.get(2).equals("0")) {
                status = false;
            }
            String preview = "";

            DVD dvd = new DVD(id, name, status,preview);
            DVDArr.add(dvd);
        }

    }

    /**
     * (带返回值)使用数据库读入DVD信息到 DVDArr 集合
     * @param isHidden 是否显示已被收入回收站的DVD（true = 回收站）
     * @param pageNum 分页当前页
     * @param pageSize 分页一页显示多少行
     * @param tableWhere 查询条件
     * @return 返回DVD集合
     */
    public static ArrayList<DVD> loadDVDInfosByArray(boolean isHidden,int pageNum
            , int pageSize,String tableWhere) {

        ArrayList<DVD> dvds = new ArrayList<>();

        List<List<String>> dvdSQLs = getDVDInfosFromJDBC(
                isHidden,pageNum
                , pageSize,tableWhere);

        for (List<String> dvdInfo : dvdSQLs) {
            //将字符串转为3种数据
            int id = Integer.valueOf(dvdInfo.get(0));
            String name = dvdInfo.get(1);
            boolean status = true;
            if (dvdInfo.get(2).equals("0")) {
                status = false;
            }
            String preview = dvdInfo.get(3);

            DVD dvd = new DVD(id, name, status,preview);
            dvds.add(dvd);
        }

        return dvds;
    }

    /**
     * (带返回值)使用数据库读入DVD信息到 DVDArr 集合
     * （不显示回收站）
     */
    public static ArrayList<DVD> loadDVDInfosByArray(int pageNum
            , int pageSize,String tableWhere) {
        return loadDVDInfosByArray(false,pageNum
                ,pageSize,tableWhere);
    }

    /**
     * 从数据库读入DVD信息到 DVDArr 集合
     * 作为 loadDVDInfos() 和 loadDVDInfosByArray()的组件
     * @return 返回DVD集合(二重)
     */
    private static List<List<String>> getDVDInfosFromJDBC(boolean isHidden,int pageNum
            , int pageSize,String tableWhere){

       String tableName = "showDVD";
       if (isHidden){
           tableName = "hiddenDVD";
       }

        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("id");
            add("name");
            add("status");
            add("preview");
        }};

        String tableOrder = " id desc ";
        String tableLimit= (pageNum-1)*pageSize+","+pageSize;

        List<List<String>> dvdSQLs = JDBCUtil
                .select(tableName,
                        tableField, tableWhere,
                        tableOrder, tableLimit);



        return dvdSQLs;
    }

    /**
     * 从数据库读入DVD信息到 DVDArr 集合
     * 作为 loadDVDInfos() 和 loadDVDInfosByArray()的组件
     * @return 返回DVD集合(二重)
     */
    private static List<List<String>> getDVDInfosFromJDBC(){
        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("id");
            add("name");
            add("status");
            add("preview");
        }};

        String tableOrder = " id desc ";

        List<List<String>> dvdSQLs = JDBCUtil
                .select("showdvd",
                        tableField,null,
                        tableOrder, null);

        return dvdSQLs;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", preview='" + preview + '\'' +
                '}';
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
