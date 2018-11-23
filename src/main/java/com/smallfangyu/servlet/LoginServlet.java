package com.smallfangyu.servlet;

import com.smallfangyu.data.DbUtil;
import com.smallfangyu.data.JdbcDruid;
import com.smallfangyu.data.LogUtil;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.annotation.WebServlet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.io.IOException;
import javax.servlet.http.HttpSession;

//@WebServlet(name = "LoginServlet",urlPatterns = {"/fy/servlet/login.do"})
@WebServlet(name = "LoginServlet",
        urlPatterns = {"/fy/servlet/login.do"}, loadOnStartup = 1)
public class LoginServlet extends javax.servlet.http.HttpServlet {
    //DbUtil db = new DbUtil();
    //阿里巴巴druid连接数据库
    JdbcDruid db=new JdbcDruid();
    private int number=0;

    public int recy(){
        number=0;
        String sql = "SELECT * FROM dvd WHERE `show`=0 ";
        ResultSet rs = db.executeQuery(sql, null);
        try {
            while (rs.next()) {
                number++;
            }
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    public boolean check(String userName,String passWord){
        String sql = "SELECT * FROM user ";
        ResultSet rs = db.executeQuery(sql, null);

        try {
            //判断用户名密码是否正确
            while (rs.next()) {
                if (!(userName.equals(rs.getString("username"))&&passWord.equals( rs.getString("password")))) {
                    //遍历到rs的最后位置
                    if (rs.isLast()) {
                        //用户名或密码错误
                        return false;
                    }
                } else {
                    break;
                }
            }
            //关闭资源
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return true;
    }

    /**
     * md5加密密码
     * @param password
     * @return
     */
    public String mdFive(String password){
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] input = password.getBytes();
            byte[] output = md.digest(input);
            //将md5处理后的output结果利用Base64转成原有的字符串,不会乱码
            String md5 = Base64.encodeBase64String(output);
            return md5;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("密码加密失败");
            return "";
        }
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String userName=request.getParameter("username");

        //调用MD5加密密码
        String passWord= mdFive(request.getParameter("password"));

        if(check(userName,passWord)){
            //创建session对象
            recy();
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("loginName", userName);
            session.setAttribute("recycle", number);
            LogUtil.getInstance().getLogger().debug("用户名:" + session.getAttribute("loginName") + " 登录");
            response.sendRedirect("/fy/servlet/toShowDvd");
        }else{
            response.getWriter().write("<script language='javascript'>alert('账号或密码错误');location.href='/fy/jsp/login.jsp';</script>");
        }

    }
}
