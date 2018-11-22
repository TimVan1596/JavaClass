package com.smallfangyu.data;

import com.smallfangyu.data.AnnotateTest;
import com.smallfangyu.data.DVD;
import com.smallfangyu.data.IDvdAnnotate;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.Reader;

/** 
* AnnotateTest Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮһ�� 22, 2018</pre> 
* @version 1.0 
*/ 
public class AnnotateTestTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    AnnotateTest att=new AnnotateTest();

@Before
public void before() throws Exception {
        reader = Resources.getResourceAsReader("dvd-config-annotate.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSessionFactory.getConfiguration().addMapper(IDvdAnnotate.class);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: deleteDvd(int id) 
* 
*/ 
@Test
public void testDeleteDvd() throws Exception { 
//TODO: Test goes here...
    int line=att.deleteDvd(1099);
    Assert.assertEquals(0,line);
} 

/** 
* 
* Method: insertDvd(DVD dvd) 
* 
*/ 
@Test
public void testInsertDvd() throws Exception { 
//TODO: Test goes here...
    DVD dvd=new DVD();
    dvd.setDvdname("mybatis插入");
    dvd.setState("可以借");
    dvd.setPicture("mybatis");
    int line=att.insertDvd(dvd);
    Assert.assertEquals(1, line);
} 

/** 
* 
* Method: updateDvd(DVD dvd) 
* 
*/ 
@Test
public void testUpdateDvd() throws Exception { 
//TODO: Test goes here...
    DVD dvd=new DVD();
    dvd.setDvdno(1101);
    dvd.setDvdname("mybatis插入");
    dvd.setState("已借出");
    dvd.setPicture("zzzzz");
    int line=att.updateDvd(dvd);
    Assert.assertEquals(1, line);
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here... 
} 


} 
