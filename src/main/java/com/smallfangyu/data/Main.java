package com.smallfangyu.data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.*;

public class Main {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("dvd-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    /**
     * //@param args
     */
    public static void main(String[] args) {
//        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //sqlSessionFactory.getConfiguration().addMapper(IUser.class);
            //User user = (User) session.selectOne( "com.yiibai.mybatis.models.UserMapper.getUserByID", 1);
            // 更新用户
            testUpdate();

            // 用户数据列表
           // getUserList();
            // 插入数据
             //testInsert();

            // 删除数据
            //testDelete();

        } finally {
            session.close();
        }
   }

    //
    public static void testInsert()
    {
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IDvd dvdMapper = session.getMapper(IDvd.class);
            System.out.println("Test insert start...");
            // 执行插入
            DVD dvd = new DVD();
            dvd.setDvdname("");
            dvd.setState("");
            dvd.setPicture("");
            dvdMapper.insertDvd(dvd);
            // 提交事务
            session.commit();

            // 显示插入之后User信息
            System.out.println("After insert");
            getUserList();
            System.out.println("Test insert finished...");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 插入DVD数据
     */
    public  int dvdInsert(String dvdname,String picture){
        int result=0;
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IDvd dvdMapper = session.getMapper(IDvd.class);
            // 执行插入
            DVD dvd = new DVD();
            dvd.setDvdname(dvdname);
            dvd.setState("可以借");
            dvd.setPicture(picture);
            dvdMapper.insertDvd(dvd);
            // 提交事务
            session.commit();
            result=1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    // 获取用户列表
    public static void getUserList() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IDvd idvd = session.getMapper(IDvd.class);
            // 显示User信息
            System.out.println("Test Get start...");
            printUsers(idvd.getDvdList());
            System.out.println("Test Get finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取dvd数据
    public List<DVD> getDvdList() {
        List<DVD> dvds=new ArrayList<>();
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IDvd idvd = session.getMapper(IDvd.class);
            dvds= idvd.getDvdList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dvds;
    }

    public static void testUpdate()
    {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IDvd idvd= session.getMapper(IDvd.class);
            System.out.println("Test update start...");
            printUsers(idvd.getDvdList());
            // 执行更新
            DVD dvd = idvd.getDvd(1099);
            dvd.setDvdname("天蚕土豆");
           dvd.setState("可以借");
           dvd.setPicture("...");
            // 提交事务
            idvd.updateDvd(dvd);
            session.commit();
            // 显示更新之后User信息
            System.out.println("After update");
            printUsers(idvd.getDvdList());
            System.out.println("Test update finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 修改dvd数据
     */
    public int dvdUpdate(int dvdno,String dvdname,String state,String picture) {
        int sucess=0;
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IDvd idvd= session.getMapper(IDvd.class);

            // 执行更新
                DVD dvd = idvd.getDvd(dvdno);
                dvd.setDvdname(dvdname);
                dvd.setState(state);
                if(picture.length()!=0) {
                    //改图片修改
                   dvd.setPicture(picture);
                   idvd.updateDvdd(dvd);
                }else {
                    //不改图片修改
                    idvd.updateDvd(dvd);
                }
            // 提交事务
                session.commit();
                sucess = 1;

        }catch (Exception e){
            e.printStackTrace();
        }
        return sucess;
    }

    // 删除用户信息
    public static void testDelete()
    {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IDvd idvd = session.getMapper(IDvd.class);
            System.out.println("Test delete start...");
            // 显示删除之前User信息
            System.out.println("Before delete");
            printUsers(idvd.getDvdList());
            // 执行删除
            idvd.deleteDvd(22);
            // 提交事务
            session.commit();
            // 显示删除之后User信息
            System.out.println("After delete");
            printUsers(idvd.getDvdList());
            System.out.println("Test delete finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 删除dvd数据
     */
    public  int dvdDelete(int dvdno) {
        int result=0;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IDvd idvd = session.getMapper(IDvd.class);
            // 执行删除
            idvd.deleteDvd(dvdno);
            // 提交事务
            session.commit();
            result=1;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * 打印用户信息到控制台
     *
     * @param dvds
     */
    private static void printUsers(final List<DVD> dvds) {
        int count = 0;

        for (DVD dvd : dvds) {
            System.out.println(MessageFormat.format("============= DVD[{0}]=================", ++count));
            System.out.println("Id: " + dvd.getDvdno());
            System.out.println("Name: " + dvd.getDvdname());
            System.out.println("状态: " + dvd.getState());
            System.out.println("图片: " + dvd.getPicture());
        }
    }
}