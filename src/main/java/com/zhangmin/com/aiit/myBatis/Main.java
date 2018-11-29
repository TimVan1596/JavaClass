package com.zhangmin.com.aiit.myBatis;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("Configure.xml");
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
            //sqlSessionFactory.getConfiguration().addMapper(IStu.class);
            //Student user = (Student) session.selectOne( "com.yiibai.mybatis.models.StuMapper.getStuByID", 1);
             //更新用户
            //testUpdate();

            // 用户数据列表
//            getStuList();
            // 插入数据
            testInsert();

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
            IStu stuMapper = session.getMapper(IStu.class);
            System.out.println("Test insert start...");
            // 执行插入
            Student stu = new Student();
            stu.setStuId(666);
            stu.setStuName("zhangsan");
            stuMapper.insertStu(stu);
            // 提交事务
            session.commit();

//            // 显示插入之后User信息
//            System.out.println("After insert");
//            getStuList();
//            System.out.println("Test insert finished...");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 获取用户列表
    public static void getStuList() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
//            IStu istu= session.getMapper(IStu.class);
            Student student =  session.selectOne("getStuList");
            session.commit();
            // 显示stu信息
            System.out.println("Test Get start...");
            System.out.println(student.getStuName());
            System.out.println("Test Get finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testUpdate()
    {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IStu istu = session.getMapper(IStu.class);
            System.out.println("Test update start...");
//            printUsers(ibook.getUserList());
            // 执行更新
            Student student = istu.getStu(2);
            student.setStuName("Lucy");
            istu.updateStu(student);
            // 提交事务
            session.commit();
            // 显示更新之后User信息
            System.out.println("After update");
            printStu(istu.getStuList());
            System.out.println("Test update finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 删除用户信息
    public static void testDelete()
    {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            IStu istu = session.getMapper(IStu.class);
            System.out.println("Test delete start...");
            // 显示删除之前User信息
            System.out.println("Before delete");
            printStu(istu.getStuList());
            // 执行删除
            istu.deleteStu(22);
            // 提交事务
            session.commit();
            // 显示删除之后User信息
            System.out.println("After delete");
//            printStu(ibook.getStuList());
            System.out.println("Test delete finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * 打印用户信息到控制台
     *
     * @param students
     */
    private static void printStu(final List<Student> students) {
        int count = 0;

        for (Student student : students) {
            System.out.println(MessageFormat.format(
                    "============= Student[{0}]=================", ++count));
            System.out.println("学号: " + student.getStuId());
            System.out.println("姓名: " + student.getStuId());
        }
    }
}