package com.smallfangyu.servlet;

import com.smallfangyu.data.DVD;
import com.smallfangyu.data.DbUtil;
import com.smallfangyu.data.LogUtil;

import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.IOException;
import javax.servlet.http.HttpSession;

//@WebServlet(name = "LoginServlet",urlPatterns = {"/fy/servlet/login.do"})
@WebServlet(name = "LoginServlet",
        urlPatterns = {"/fy/servlet/login.do"}, loadOnStartup = 1)
public class LoginServlet extends javax.servlet.http.HttpServlet {
    DbUtil db = new DbUtil();
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
                if (!(userName.equals(rs.getString("username"))&&passWord.equals(rs.getString("password")))) {
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

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        String userName=request.getParameter("username");
        String passWord=request.getParameter("password");

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
            //response.setContentType("text/html;charset=UTF-8");
            //response.getWriter().write("账号或密码错误");

            response.getWriter().write("<script language='javascript'>alert('账号或密码错误');location.href='/fy/jsp/login.jsp';</script>");
        }

    }
}
