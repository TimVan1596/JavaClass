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
            // 1、新增一个部门，总裁办
//            InsertDept();

            // 2、新增一个员工，叫 赵六，分配到总裁办
//            InsertUser();

            // 3、更改张三的部门，调到总裁办
//            UpdateUser();

            // 4、把技术部的员工，都合并到市场部
//            UpdateDept();

            // 删除数据
            //testDelete();

        } finally {
            session.close();
        }
    }

    // 添加部门
    public static void InsertDept() {
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IUser userMapper = session.getMapper(IUser.class);
            // 执行插入
            User user = new User();
            user.setDeptName("总裁办");
            userMapper.insertDept(user);
            // 提交事务
            session.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 添加员工
    public static void InsertUser() {
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IUser userMapper = session.getMapper(IUser.class);
            // 执行插入
            User user = new User();
            user.setUserName("赵六");
            user.setUserDept("总裁办");
            user.setUserAge(23);
            userMapper.insertUser(user);
            // 提交事务
            session.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 修改用户列表
    public static void UpdateUser() {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 执行更新
            User user = iuser.getUser("张三");
            user.setUserDept("总裁办");
            iuser.updateUser(user);
            // 提交事务
            session.commit();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 修改用户部门
    public static void UpdateDept() {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 执行更新
            User user = iuser.getDept("技术部");
            user.setUserDept("总裁办");
            iuser.updateUser(user);
            // 提交事务
            session.commit();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 删除用户信息
    public static void DeleteUser() {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 执行删除
            iuser.deleteUser(5);
            // 提交事务
            session.commit();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}