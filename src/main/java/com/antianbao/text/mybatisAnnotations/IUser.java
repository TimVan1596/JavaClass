package com.antianbao.text.mybatisAnnotations;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 *
 * @author yiibai
 *
 */
public interface IUser {
    // 显示所有
    @Select("SELECT *FROM user")
    public List<User> getUserList();

    // 通过员工姓名查找
    @Select("SELECT *FROM user WHERE userName = #{userName}")
    public User getUser(String userName);

    // 通过员工部门查找
    @Select("SELECT *FROM user WHERE userDept = #{userDept}")
    public User getDept(String userDept);

    // 添加员工信息
    @Insert("INSERT INTO user(userName,userAge,userSex,userDept) " +
            "VALUES(#{userName},#{userAge},#{userSex},#{userDept})")
    public void insertUser(User user);

    // 添加部门信息
    @Insert("INSERT INTO dept(deptName) VALUES(#{deptName})")
    public void insertDept(User user);

    // 通过部门修改部门
    @Update("UPDATE user SET userDept=#{userDept} WHERE userName=#{userName}")
    public void updateDept(User user);

    // 通过姓名修改部门
    @Update("UPDATE user SET userDept=#{userDept} WHERE userName=#{userName}")
    public void updateUser(User user);

    // 查询80后的员工
    @Select("SELECT *FROM user WHERE userAge < 38 AND userAge > 28")
    public List<User> getUserAge();

    // 查询80后且性别为男的员工
    @Select("SELECT *FROM user WHERE userAge < 38 AND userAge > 28 AND userSex = '男'")
    public List<User> getUserAgeSex();

    @Delete("DELETE FROM user WHERE userId = #{userId}")
    public void deleteUser(int id);

}