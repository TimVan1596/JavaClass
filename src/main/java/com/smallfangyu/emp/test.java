package com.smallfangyu.emp;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

public class test {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("emp-department-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询emp数据
    public static void empList() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IEmp iemp = session.getMapper(IEmp.class);
            List<Emp> emps=iemp.selectEmp();
            int i=1;
            for(Emp emp:emps){
                System.out.println("----------"+(i++)+"----------");
                System.out.println("员工编号:"+emp.getDno());
                System.out.println("员工姓名:"+emp.getEmpname());
                System.out.println("员工性别:"+emp.getSex());
                System.out.println("员工生日:"+emp.getBirthday());
                System.out.println("员工部门编号:"+emp.getDno());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 连表查询emp数据
    public static void empAndDepartmentList() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IEmp iemp = session.getMapper(IEmp.class);
            List<Emp> emps=iemp.selectEmpAndDepartment();
            int i=1;
            for(Emp emp:emps){
                System.out.println("----------"+(i++)+"----------");
                System.out.println("员工编号:"+emp.getDno());
                System.out.println("员工姓名:"+emp.getEmpname());
                System.out.println("员工性别:"+emp.getSex());
                System.out.println("员工生日:"+emp.getBirthday());
                System.out.println("员工部门:"+emp.getDepartment().getDname());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改员工部门
     * @return
     */
    public static int updateDepartment(int empno,int dno){
        int result=0;
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IEmp iemp = session.getMapper(IEmp.class);
            // 执行插入
            Emp emp = new Emp();
            emp.setEmpno(empno);
            emp.setDno(dno);
            iemp.updateEmp(emp);
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

    /**
     * 添加新员工
     * @return
     */
    public static int addEmp(String name,String sex,String birthday,int dno){
        int result=0;
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IEmp iemp = session.getMapper(IEmp.class);
            // 执行插入
            Emp emp = new Emp();
            emp.setEmpname(name);
            emp.setSex(sex);
            emp.setBirthday(birthday);
            emp.setDno(dno);
            iemp.addEmp(emp);
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

    /**
     * 添加新部门
     */
    public static int addDepart(String dname){
        int result=0;
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            IDepartment idepart = session.getMapper(IDepartment.class);
            // 执行插入
            Department depart = new Department();
            depart.setDname(dname);
            idepart.addDepartment(depart);
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

    public static void main(String[] args){
        //empList();
        //System.out.println(addDepart("总裁办"));
        //System.out.println(addEmp("赵六","男","1995-8-11",4));
        //System.out.println(updateDepartment(1,4));
        empAndDepartmentList();
    }
}
