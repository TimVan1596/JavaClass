package com.smallfangyu.bookjavaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "BookLoginServlet",urlPatterns = {"/book/login"})
public class BookLoginServlet extends HttpServlet {
    DbUtil db = new DbUtil();

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String userName=request.getParameter("username");
    String passWord=request.getParameter("password");
    if(check(userName,passWord)){
        //创建session对象
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        session.setAttribute("loginName", userName);
        response.sendRedirect("/book/showbook");
    }else{
        //response.setContentType("text/html;charset=UTF-8");
        //response.getWriter().write("账号或密码错误");

        response.getWriter().write("<script language='javascript'>alert('账号或密码错误');location.href='/fy/book/jsp/login.jsp';</script>");
    }
    }
}
