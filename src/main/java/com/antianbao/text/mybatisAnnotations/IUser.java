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
    //public User getUserByID(int id);
    @Select("SELECT * FROM mybatis")
    public List<User> getUserList();

    @Insert("INSERT INTO mybatis(name,\n" +
            "        dept, website,phone)\n" +
            "        VALUES(#{name}, #{dept}, #{website}, #{phone})")
    public void insertUser(User user);

    @Update("UPDATE mybatis\n" +
            "        SET\n" +
            "        name=\n" +
            "        #{name},\n" +
            "        dept = #{dept},\n" +
            "        website = #{website},\n" +
            "        phone = #{phone}\n" +
            "        WHERE\n" +
            "        id =\n" +
            "        #{id}")
    public void updateUser(User user);

    @Delete("DELETE FROM mybatis WHERE id = #{id}")
    public void deleteUser(int userId);

    @Select("SELECT *FROM mybatis WHERE id = #{userId}")
    public User getUser(int id);
}