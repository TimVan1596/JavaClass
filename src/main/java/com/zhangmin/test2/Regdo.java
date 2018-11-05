package com.zhangmin.test2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/Reg.do")
public class Regdo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String userName=request.getParameter("userName");
           String userPassword=request.getParameter("userPassword");
           response.setContentType("text/html; charset=utf-8");
           if(userName.equals(userPassword)){
               response.getWriter().print("注册成功！");
           }else{
               response.getWriter().print("注册失败！");
           }
    }
}
