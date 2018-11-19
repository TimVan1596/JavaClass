package com.smallfangyu.bookjavaweb;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class HelloWord {
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
            Book book = (Book) session.selectOne(
                    "com.smallfangyu.bookjavaweb.BookMapper.GetUserByID", 3);
            if(book!=null){
                String userInfo = "名字："+book.getName()+", 作者："+book.getAuthor()+", 价格："+book.getPrice();
                System.out.println(userInfo);
            }
        } finally {
            session.close();
        }
    }
}
