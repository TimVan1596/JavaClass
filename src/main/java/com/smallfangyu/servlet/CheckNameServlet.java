package com.smallfangyu.servlet;

import com.smallfangyu.data.DbUtil;
import com.smallfangyu.data.JdbcDruid;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "CheckNameServlet",urlPatterns = {"/fy/toCheckName"})
public class CheckNameServlet extends HttpServlet {
   // DbUtil db = new DbUtil();
   //阿里巴巴druid连接数据库
   JdbcDruid db=new JdbcDruid();
    public boolean checkname(String userName){
        String sql = "SELECT * FROM user ";
        ResultSet rs = db.executeQuery(sql, null);
        try {
            //判断用户名是否存在
            while (rs.next()) {
                if (!(userName.equals(rs.getString("username")))) {
                    //遍历到rs的最后位置
                    if (rs.isLast()) {
                        //用户名不存在
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
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //取值
      String name=  request.getParameter("login");
      //判断  (数据库判断，留给你一个坑)
      int atpos = name.indexOf("@");
      int dotpos = name.lastIndexOf(".");

      if(checkname(name)) {
          //跳转
      request.getSession().setAttribute("MSG", "<font color='red'>sorry,账号已被注册</font>");
      request.getSession().setAttribute("name", name);
      response.sendRedirect("/fy/jsp/register.jsp");

         // response.getWriter().print("账号已被注册");
      } else {
          if(atpos<1 || dotpos<atpos + 2 ) {
              request.getSession().setAttribute("MSG", "<font color='red'>sorry,邮箱格式不正确</font>");
              request.getSession().setAttribute("name", name);
              response.sendRedirect("/fy/jsp/register.jsp");
              //response.getWriter().print("邮箱格式不正确");
          }else{
       request.getSession().setAttribute("MSG", "<font color='green'>恭喜，账号可用</font>");
       request.getSession().setAttribute("name", name);
       response.sendRedirect("/fy/jsp/register.jsp");
              //response.getWriter().print("账号可用");
          }
      }
    }
}
