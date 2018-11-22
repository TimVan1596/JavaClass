package com.smallfangyu.data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;
import java.util.List;


public class AnnotateTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("dvd-config-annotate.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(IDvdAnnotate.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public static int deleteDvd(int id){
        SqlSession session = sqlSessionFactory.openSession();
        int result=0;
        try {
            IDvdAnnotate ida = session.getMapper(IDvdAnnotate.class);
           result=(ida.deleteDvdById(id));
           session.commit();
        }finally {
            session.close();
        }
        return  result;
    }

    /**
     * 增加数据
     * @param dvd
     * @return
     */
    public static int insertDvd(DVD dvd){
        SqlSession session = sqlSessionFactory.openSession();
        int result=0;
        try {
            IDvdAnnotate ida = session.getMapper(IDvdAnnotate.class);
            result=(ida.insertDvd(dvd));
            session.commit();
        }finally {
            session.close();
        }
        return  result;
    }

    /**
     * 修改数据
     * @param dvd
     * @return
     */
    public static int updateDvd(DVD dvd){
        SqlSession session = sqlSessionFactory.openSession();
        int result=0;
        try {
            IDvdAnnotate ida = session.getMapper(IDvdAnnotate.class);
            result=(ida.updateDvd(dvd));
            session.commit();
        }finally {
            session.close();
        }
        return  result;
    }

    public static void main(String[] args){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IDvdAnnotate ida = session.getMapper(IDvdAnnotate.class);
            List<DVD> dvds= ida.getDvd();
            int i=1;
            for(DVD dvd:dvds) {
                System.out.println("--------"+(i++)+"--------");
                System.out.println(dvd.getDvdno());
                System.out.println(dvd.getDvdname());
                System.out.println(dvd.getState());
                System.out.println(dvd.getPicture());
                System.out.println(dvd.getSta());
            }
        }finally {
            session.close();
        }

        System.out.println(deleteDvd(1101));

//        DVD dvd=new DVD();
//        dvd.setDvdname("mybatis插入");
//        dvd.setState("可以借");
//        dvd.setPicture("mybatis");
//        System.out.println(insertDvd(dvd));

//        DVD dvd=new DVD();
//        dvd.setDvdno(1101);
//        dvd.setDvdname("mybatis插入");
//        dvd.setState("已借出");
//        dvd.setPicture("zzzzz");
//        System.out.println(updateDvd(dvd));
}
}
