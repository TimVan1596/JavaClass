package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.JDBCUtil;
import com.timvanx.biggerdvd.util.RandomCAPCHA;

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
    public static boolean changePasswordByEmail(String email, String password) {
        boolean isAccountExist = false;

        //判断账户名是否存在
        if (isAccountExist(email)) {

            isAccountExist = true;
            //在数据库中update密码
            Map<String, Object> updateData =
                    new HashMap<>(1);
            updateData.put("password", password);
            String tableWhere = "email = " + " '" + email + "' ";
            JDBCUtil.update("account", updateData
                    , tableWhere);
        }

        return isAccountExist;
    }

    /**
     * 通过用户名修改密码
     */
    public static String updateCAPCHA(String email){

        String CAPTCHA =  RandomCAPCHA.createRandomCAPCHA();
        String tableWhere = "email = " + " '" + email + "' ";

        //更新用户表中的验证码
        Map<String, Object> updateData =
                new HashMap<>(1);
        updateData.put("capcha", CAPTCHA);
        JDBCUtil.update("account", updateData
                , tableWhere);

        return CAPTCHA;
    }

    /**
     * 通过用户名，检查验证码是否正确
     */
    public static boolean checkCAPCHA(String email , String capcha){
        boolean isSuccessful = false;

        String tableWhere = "email = " + " '" + email + "' ";
        tableWhere += " and capcha = " + " '" + capcha + "' ";

        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("email");
        }};

        int cnt  = JDBCUtil.count("account",
                "email",tableWhere);
        if (cnt == 1){
            isSuccessful = true;
        }


        return isSuccessful;
    }

    /**
     * 获取权限值
     * @param tableWhere 查询条件
     * @return authority String authority=权限值
     */
    public static String getAuthority(String tableWhere) {

        String authority = "";

        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("authority");
        }};
        List<List<String>> isfirstloginList =
                JDBCUtil.select("account",
                        tableField, tableWhere, null,
                        null);

        authority = isfirstloginList.get(0).get(0);

        return authority;
    }

    /**
     * 获取权限值
     * @param tableWhere 查询条件
     * @return authority String authority=权限值
     */
    public static String getName(String tableWhere){
        String name = "";

        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("name");
        }};
        List<List<String>> isfirstloginList =
                JDBCUtil.select("account",
                        tableField, tableWhere, null,
                        null);

        name = isfirstloginList.get(0).get(0);

        return name;
    }
}
