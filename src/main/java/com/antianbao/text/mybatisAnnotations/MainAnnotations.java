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

            // 5、全部展示所有部门的所有员工
//            getUserList();

            // 6、查询80后的员工有哪些
//            getUserAge();

            // 7、查询80后的且性别为男的员工有哪些
//            getUserAgeSex();

            // 删除数据
//            DeleteUser();

        } finally {
            session.close();
        }
    }

    // 添加部门
    public static void InsertDept() {
        try {
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 添加员工
    public static void InsertUser() {
        try {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IUser userMapper = session.getMapper(IUser.class);
            // 执行插入
            User user = new User();
            user.setUserName("赵六");
            user.setUserAge(33);
            user.setUserSex("男");
            user.setUserDept("总裁办");
            userMapper.insertUser(user);
            // 提交事务
            session.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 通过姓名修改用户部门
    public static void UpdateUser() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 执行更新
            List<User> users = iuser.getUser("张三");
            for (User user : users) {
                user.setUserDept("总裁办");
                iuser.updateUser(user);
            }
            // 提交事务
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 通过部门修改用户部门
    public static void UpdateDept() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 执行更新
            List<User> users = iuser.getDept("技术部");
            for (User user : users) {
                user.setUserDept("市场部");
                iuser.updateUser(user);
            }
            // 提交事务
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取用户列表
    public static void getUserList() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 显示User信息
            printUsers(iuser.getUserList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询80后的员工
    public static void getUserAge() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 显示User信息
            printUsers(iuser.getUserAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询80后且性别为男的员工
    public static void getUserAgeSex() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 显示User信息
            printUsers(iuser.getUserAgeSex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //打印用户信息到控制台
    private static void printUsers(final List<User> users) {
        int count = 0;
        for (User user : users) {
            System.out.println(MessageFormat.format(
                    "============= 员工表[{0}]=================", ++count));
            System.out.println("工号: " + user.getUserId());
            System.out.println("姓名: " + user.getUserName());
            System.out.println("年龄: " + user.getUserAge());
            System.out.println("性别: " + user.getUserSex());
            System.out.println("部门: " + user.getUserDept());

        }
    }

    // 删除用户信息
    public static void DeleteUser() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IUser iuser = session.getMapper(IUser.class);
            // 执行删除
            iuser.deleteUser(5);
            // 提交事务
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}