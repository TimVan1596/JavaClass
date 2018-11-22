package com.smallfangyu.servlet;

import com.smallfangyu.data.DVD;
import com.smallfangyu.data.DbUtil;
import com.smallfangyu.data.JdbcDruid;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RecycleServlet",urlPatterns = {"/fy/recycle"})
public class RecycleServlet extends HttpServlet {
    //DbUtil db=new DbUtil();
//阿里巴巴druid连接数据库
    JdbcDruid db=new JdbcDruid();
    /**所有DVD集合
     *
     */
     ArrayList<DVD> list=new ArrayList<DVD>();
    public  void dvdList() {
        if (!list.isEmpty()) {
            list.clear();
        }
        ResultSet rs;

        String sql = "SELECT * FROM dvd WHERE `show`=0 ";
        rs = db.executeQuery(sql, null);
        try {
            while (rs.next()) {
                list.add(new DVD(rs.getInt("dvdno"), rs.getString("dvdname"), rs.getString("state"), rs.getString("picture")));
            }
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     dvdList();
        //创建session对象
        HttpSession session = request.getSession();

        //把用户数据保存在session域对象中
        session.setAttribute("deletelistDVD", list);

        response.sendRedirect("/fy/jsp/recycle.jsp");
    }
}
