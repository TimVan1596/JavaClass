package com.smallfangyu.bookjavaweb;

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
            //sqlSessionFactory.getConfiguration().addMapper(IUser.class);
            //User user = (User) session.selectOne( "com.yiibai.mybatis.models.UserMapper.getUserByID", 1);
// 更新用户
            testUpdate();

            // 用户数据列表
            getUserList();
            // 插入数据
            // testInsert();

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
            IBook userMapper = session.getMapper(IBook.class);
            System.out.println("Test insert start...");
            // 执行插入
            Book book = new Book();
            book.setId(0);
            book.setName("Google");
            book.setAuthor("Tech");
            book.setPrice(100);
            book.setDate("2018/11/19");
            userMapper.insertUser(book);
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

    // 获取用户列表
    public static void getUserList() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IBook ibook = session.getMapper(IBook.class);
            // 显示User信息
            System.out.println("Test Get start...");
            printUsers(ibook.getUserList());
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
            IBook ibook = session.getMapper(IBook.class);
            System.out.println("Test update start...");
            printUsers(ibook.getUserList());
            // 执行更新
            Book book = ibook.getUser(2);
            book.setName("New name");
            book.setAuthor("啧啧");
            book.setPrice(99);
            book.setDate("111");
            ibook.updateUser(book);
            // 提交事务
            session.commit();
            // 显示更新之后User信息
            System.out.println("After update");
            printUsers(ibook.getUserList());
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
            IBook ibook = session.getMapper(IBook.class);
            System.out.println("Test delete start...");
            // 显示删除之前User信息
            System.out.println("Before delete");
            printUsers(ibook.getUserList());
            // 执行删除
            ibook.deleteUser(2);
            // 提交事务
            session.commit();
            // 显示删除之后User信息
            System.out.println("After delete");
            printUsers(ibook.getUserList());
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
     * @param users
     */
    private static void printUsers(final List<Book> users) {
        int count = 0;

        for (Book book : users) {
            System.out.println(MessageFormat.format(
                    "============= Book[{0}]=================", ++count));
            System.out.println("Id: " + book.getId());
            System.out.println("Name: " + book.getName());
            System.out.println("作者: " + book.getAuthor());
            System.out.println("价格: " + book.getPrice());
            System.out.println("日期: " + book.getDate());
        }
    }
}