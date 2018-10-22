package com.smallfangyu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
@WebServlet(name = "CancellingServlet",urlPatterns = {"/fy/servlet/toCancelling"})
public class CancellingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //false代表：不创建session对象，只是从request中获取。
       HttpSession session = request.getSession(false);
       if(session!=null){

       session.removeAttribute("loginName");
       //从定向到login.jsp
       response.sendRedirect("/fy/jsp/login.jsp");
       }
    }
}
