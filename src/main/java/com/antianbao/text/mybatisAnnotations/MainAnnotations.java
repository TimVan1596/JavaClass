package com.antianbao.text.mybatisAnnotations;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;

public class MainAnnotations {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("ConfigureAnnotations.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //sqlSessionFactory.getConfiguration().addMapper(IUser.class);
            //User user = (User) session.selectOne( "com.yiibai.mybatis.models.UserMapper.getUserByID", 1);

            // 用户数据列表
            getUserList();
            // 插入数据
            //testInsert();

            // 更新用户
            //testUpdate();

            // 删除数据
            //testDelete();

        } finally {
            session.close();
        }
    }

    //
    public static void testInsert() {
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IUser userMapper = session.getMapper(IUser.class);
            System.out.println("开始插入");
            // 执行插入
            User user = new User();
            user.setEmail("atb");
            user.setPassword("123");
            userMapper.insertUser(user);
            // 提交事务
            session.commit();

            // 显示插入之后User信息
            System.out.println("插入前");
            getUserList();
            System.out.println("插入后");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 获取用户列表
    public static void getUserList() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 显示User信息
            System.out.println("开始显示数据库内容");
            printUsers(iuser.getUserList());
            System.out.println("显示完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testUpdate() {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            System.out.println("开始修改");
            printUsers(iuser.getUserList());
            // 执行更新
            User user = iuser.getUser(5);
            user.setEmail("New name");
            iuser.updateUser(user);
            // 提交事务
            session.commit();
            // 显示更新之后User信息
            System.out.println("修改前");
            printUsers(iuser.getUserList());
            System.out.println("修改后");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 删除用户信息
    public static void testDelete() {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            System.out.println("开始删除");
            // 显示删除之前User信息
            System.out.println("删除借书");
            printUsers(iuser.getUserList());
            // 执行删除
            iuser.deleteUser(5);
            // 提交事务
            session.commit();
            // 显示删除之后User信息
            System.out.println("删除前");
            printUsers(iuser.getUserList());
            System.out.println("删除后");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * 打印用户信息到控制台
     *
     * @param users
     */
    private static void printUsers(final List<User> users) {
        int count = 0;

        for (User user : users) {
            System.out.println(MessageFormat.format(
                    "============= User[{0}]=================", ++count));
            System.out.println("User Id: " + user.getEmail());
            System.out.println("User Name: " + user.getPassword());
        }
    }
}