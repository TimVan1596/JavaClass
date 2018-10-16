package web.fy.servlet;

import web.fy.data.DVD;
import web.fy.data.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "ShowdvdServlet",urlPatterns = {"/fy/servlet/toShowDvd"})
public class ShowdvdServlet extends HttpServlet {
    static ArrayList<DVD> list=new ArrayList<DVD>();

    /**
     * 把数据库dvd表里的数据添加进数组里
     */
    public void dvdList(){
    if(!list.isEmpty()){
        list.clear();
    }
    DbUtil db=new DbUtil();
    String sql = "SELECT * FROM dvd";
    ResultSet rs = db.executeQuery(sql, null);

    try {
        while(rs.next()){
            list.add(new DVD(rs.getInt("dvdno"),rs.getString("dvdname"),rs.getString("state")));
        }

        db.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      dvdList();
       request.setCharacterEncoding("UTF-8");
       //创建session对象
       HttpSession session = request.getSession();
       //把用户数据保存在session域对象中
       session.setAttribute("listDVD", list);
       response.sendRedirect("/fy/jsp/showdvd.jsp");
    }
}
