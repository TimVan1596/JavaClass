package com.antianbao.text.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;
/**
 *
 * @author yiibai
 *
 */
public interface IUser {
    //@Select("select * from user where id= #{id}")
    //public Dept getUserByID(int id);
    public List<User> getUserList();

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(int userId);

    public User getUser(int id);
}