package com.timvanx.biggerdvd.dvd;

import com.timvanx.biggerdvd.util.Constants;
import com.timvanx.biggerdvd.util.JDBCUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户类
 *
 * @author TimVan
 * @date 2018年9月5日09:33:27
 */
public class Account implements Serializable {
    private String usrName;
    private String usrPassword;

    /**
     * serialVersionUID = 版本一致性
     * accountArrayList  = 保存所有的账户-密码
     * loginCNT = 保存用户尝试登陆的次数
     */
    private static final long serialVersionUID = 1L;
    private static ArrayList<Account> accountArrayList;
    private static int loginCNT;

    static {
        accountArrayList = new ArrayList<>();
        loginCNT = 0;
    }

    private Account(String usrName, String usrPassword) {
        this.usrName = usrName;
        this.usrPassword = usrPassword;
    }

    public Account() {
        //从文件中读取所有的账户-密码信息
        loadAccount();
    }

    /**
     * 登录
     *
     * @return boolean 是否登录成功
     */
    public static boolean login(String name, String password) {
        boolean isLogin = false;

        //判断账户和密码是否存在和正确
        for (Account account : accountArrayList) {
            if (account.getUsrName().equals(name)) {
                if (account.getUsrPassword().equals(password)) {
                    System.out.println("登录成功！");
                    isLogin = true;
                }
                break;
            }
        }

        if (!isLogin) {
            System.out.println("登录失败！请检查用户名或密码是否输入错误");
        }

        return isLogin;
    }

    /**
     * 注册
     *
     * @return boolean 是否注册成功
     */
    public static boolean register(String name, String password) {
        //检查用户名是否已存在
        boolean isNameIsexist = isAccountExist(name);

        //若用户名不存在创建用户
        if (!isNameIsexist) {
            accountArrayList.add(new Account(name, password));
            Map<String, Object> insertData =
                    new HashMap<String, Object>(1);
            insertData.put("name", name);
            insertData.put("password", password);
            JDBCUtil.insert("account", insertData);
        }

        return !isNameIsexist;
    }

    public static int getLoginCNT() {
        return loginCNT;
    }

    public static void setLoginCNT(int loginCNT) {
        Account.loginCNT = loginCNT;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    /**
     * 通过用户名检查该账户是否存在
     */
    public static boolean isAccountExist(String name) {
        boolean isNameIsexist = false;
        //判断账户名是否存在和正确
        for (Account account : accountArrayList) {
            if (account.getUsrName().equals(name)) {
                isNameIsexist = true;
                break;
            }
        }
        return isNameIsexist;
    }

    /**
     * 使用数据库从配置文件Constants.DB_CONFIG_FILE读入到accountArrayList集合
     */
    public static void loadAccount() {

        JDBCUtil.setConfigFile(Constants.DB_CONFIG_FILE);
        //设置查询条件
        ArrayList<String> tableField = new ArrayList<String>() {{
            add("name");
            add("password");
        }};
        List<List<String>> accountSQLs = JDBCUtil.select("account",
                tableField, null, null, null);
        for (List<String> accountInfo : accountSQLs) {
            Account account = new Account(
                    accountInfo.get(0),
                    accountInfo.get(1)
            );
            accountArrayList.add(account);
        }

    }

    /**
     * 通过用户名修改密码
     */
    public static boolean changePasswordByName(String name, String password) {
        boolean isAccountExist = false;

        //判断账户名是否存在
        if (isAccountExist(name)) {

            for (int i = 0; i < accountArrayList.size(); i++) {
                Account account = accountArrayList.get(i);
                if (account.getUsrName().equals(name)) {
                    //修改密码
                    account.setUsrPassword(password);
                    isAccountExist = true;
                    //在数据库中update密码
                    Map<String, Object> updateData =
                            new HashMap<>(1);
                    updateData.put("password", password);
                    String tableWhere = "name = " + " '" + name + "' ";
                    JDBCUtil.update("account", updateData
                            , tableWhere);
                    break;
                }
            }

        }

        return isAccountExist;
    }
}
