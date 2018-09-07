package com.timvanx.biggerdvd.dvd;

/**
 * DVD类
 * @author TimVan
 * @date 2018年9月6日09:16:30
 */
public class DVD {
    /**
     * cnt = 随着构造函数自增(ID赋值)
     * status = 未借出为false，反之亦然
     * */
    private static int cnt = 1000;
    private int id;
    private String name;
    private boolean status;


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

    @Override
    public String toString() {
        return id + "\t" + name + "\t\t"+(status==true?"已借出":"未借出");
    }
}
