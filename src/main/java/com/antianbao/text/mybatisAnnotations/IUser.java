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
    @Select("SELECT *FROM user,dvd")
    public List<EUser> getUserList();

    @Insert("INSERT INTO user(email,password) VALUES(#{email}, #{password})")
    public void insertUser(User user);

    @Update("UPDATE user SET email=#{email},password=#{password} WHERE id=#{id}")
    public void updateUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    public void deleteUser(int userId);

    @Select("SELECT *FROM user WHERE id = #{id}")
    public User getUser(int id);
}