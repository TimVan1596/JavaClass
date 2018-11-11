package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.JDBCUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户类
 * 全面支持 Web ， 去除 JavaSE 时代部分接口
 *
 * @author TimVan
 * @date 2018年10月27日19:40:46
 */
public class Account implements Serializable {


    /**
     * 登录
     *
     * @return boolean 是否登录成功
     */
    public static boolean login(String email, String password) {
        boolean isLogin = false;

        String tableName = "account";
        String tableWhere = "email = '" +email+"' ";
        tableWhere += "and password = '" +password+"' ";

        int cnt  = JDBCUtil.count(tableName,
                "id",tableWhere);
        if (cnt == 1){
            isLogin = true;
        }

        return isLogin;
    }

    /**
     * 注册
     *
     * @return boolean 是否注册成功
     */
    public static boolean register(String email, String password,String name) {
        //检查用户名是否已存在
        boolean isNameIsexist = isAccountExist(email);

        //若用户名不存在创建用户
        if (!isNameIsexist) {
            Map<String, Object> insertData =
                    new HashMap<String, Object>(1);
            insertData.put("email", email);
            insertData.put("password", password);
            if (name != null){
                insertData.put("name", name);
            }
            JDBCUtil.insert("account", insertData);
        }

        return !isNameIsexist;
    }

    /**
     * 注册
     *
     * @return boolean 是否注册成功
     */
    public static boolean register(String email, String password){
        return register(email,password,null);
    }


    /**
     * 获取用户是否首次登录
     * @param tableWhere 查询条件
     * @return isfirstlogin boolean true=首次登录
     */
    public static boolean isFirstLogin(String tableWhere) {

        boolean isfirstlogin = false;

        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("isfirstlogin");
        }};
        List<List<String>> isfirstloginList =
                JDBCUtil.select("account",
                tableField, tableWhere, null,
                        null);
        if ("1".equals( isfirstloginList.get(0).get(0))){
            isfirstlogin = true;

            //若首次登录则检测后，覆盖为false
            Map<String, Object> updateData =
                    new HashMap<>(1);
            updateData.put("isfirstlogin", "0");
            JDBCUtil.update("account", updateData
                    , tableWhere);
        }

        return isfirstlogin;
    }

    /**
     * 通过邮箱检查该账户是否存在
     */
    public static boolean isAccountExist(String email) {
        boolean isNameIsexist = false;

        String tableName = "account";
        String tableWhere = "email = '" +email+"' ";

        int cnt  = JDBCUtil.count(tableName,
                "id",tableWhere);
        if (cnt == 1){
            isNameIsexist = true;
        }

        return isNameIsexist;
    }


    /**
     * 通过用户名修改密码
     */
    public static boolean changePasswordByName(String name, String password) {
        boolean isAccountExist = false;

        //判断账户名是否存在
        if (isAccountExist(name)) {

            isAccountExist = true;
            //在数据库中update密码
            Map<String, Object> updateData =
                    new HashMap<>(1);
            updateData.put("password", password);
            String tableWhere = "name = " + " '" + name + "' ";
            JDBCUtil.update("account", updateData
                    , tableWhere);

        }

        return isAccountExist;
    }


}
