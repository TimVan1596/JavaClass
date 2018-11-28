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
    //@Select("select * from user where id= #{id}")
    //public Dept getUserByID(int id);
    @Select("SELECT *FROM user WHERE userName = #{userName}")
    public User getUser(String userName);

    @Select("SELECT *FROM user WHERE userDept = #{userDept}")
    public User getDept(String userDept);

    @Insert("INSERT INTO user(userName,userDept,userAge) VALUES(#{userName},#{userDept},#{userAge})")
    public void insertUser(User user);

    @Insert("INSERT INTO dept(deptName) VALUES(#{deptName})")
    public void insertDept(User user);

    @Update("UPDATE user SET userDept=#{userDept} WHERE userDept=#{userDept}")
    public void updateUserDept(User user);

    @Update("UPDATE user SET userDept=#{userDept} WHERE userName=#{userName}")
    public void updateUser(User user);

    @Update("UPDATE user SET userDept=#{userDept} WHERE userName=#{userName}")
    public void updateDept(User user);

    @Delete("DELETE FROM user WHERE userId = #{userId}")
    public void deleteUser(int id);

}