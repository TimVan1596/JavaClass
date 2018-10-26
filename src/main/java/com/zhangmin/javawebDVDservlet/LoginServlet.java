package com.zhangmin.javawebDVDservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * 编辑的DVD的信息
 * @author zhangmin
 */
    @WebServlet(name = "LoginServlet",
        urlPatterns = {"/zm/DVD/login.do"}, loadOnStartup = 1)
    public class LoginServlet extends HttpServlet {
        //连接数据库
      DBHelp dbHelp = new DBHelp();
      public boolean checkLogin(String userName,String password) {
          String sql = "select * from tb_manager";
          PreparedStatement pre = dbHelp.getPre(sql);
          ResultSet rs;

          {
              try {
                  rs = pre.executeQuery();
                  //判断用户名和密码是否正确
                  while(rs.next()){
                      if(!(userName.equals(rs.getString("name")) && password.equals(rs.getString("password")))){
                        if(rs.isLast()){
                            return  false;
                        }
                      }else{
                          break;
                      }
                  }
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
         return  true;
      }
      @Override
      protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
          //解决中文乱码
          request.setCharacterEncoding("UTF-8");
      }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
        String userName=request.getParameter("username");
        String passWord=request.getParameter("password");
        if(checkLogin(userName,passWord)){
            HttpSession session = request.getSession();
            session.setAttribute("loginName", userName);
            response.sendRedirect("/zm/DVD/showDVD.do");
        }else{
            response.getWriter().write("<script language='javascript'>alert('账号或密码错误!!!');location.href='/zm/DVD/login.jsp';</script>");
        }

    }
}