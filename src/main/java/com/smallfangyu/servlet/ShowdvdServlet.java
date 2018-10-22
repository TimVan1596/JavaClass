package com.smallfangyu.servlet;

import com.smallfangyu.data.DVD;
import com.smallfangyu.data.DbUtil;

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
    static DbUtil db=new DbUtil();
    /**所有DVD集合
     *
     */
    ArrayList<DVD> list=new ArrayList<DVD>();

    /**当前页面DVD集合
     *
     */
    ArrayList<DVD> list_page = new ArrayList<DVD>();

    /**定义已借出和可以借数量
     *
     */
    static int cSize=0;
    static int ncSize=0;

    /**
     * 定义dvd数据个数
     */
    static int dvdLength=0;

    /**
     * 定义一个页面多少条数据
     */
    static int pageSize=7;

    /**
     * 定义一共的页数
     */
    static int pageNumber=0;



        /**
         * 查询数据库一共几条数据
         */
        public void count() {
            String sq = "SELECT COUNT(*) FROM dvd ";
            ResultSet rst = db.executeQuery(sq, null);
            try {
                while (rst.next()) {
                    dvdLength = rst.getInt("COUNT(*)");
                }
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (dvdLength % pageSize == 0) {
                pageNumber = (dvdLength / pageSize) - 1;
            } else {
                pageNumber = dvdLength / pageSize;
            }
        }

        /**
         *查询DVD已借出和可以借的数量
         */
        public void nc(){
        String sql = "SELECT COUNT(*) FROM dvd WHERE state=?";
        Object[] params={"可以借"};
        ResultSet rs = db.executeQuery(sql, params);

        Object[] params2={"已借出"};
        ResultSet rss = db.executeQuery(sql, params2);
        try {
            while (rs.next()) {
                cSize=rs.getInt("COUNT(*)");
            }
            while (rss.next()) {
                ncSize=rss.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }

    /**
     * 把数据库dvd表里的数据添加进数组里
     */
    public void dvdList(String data,int page) {
        if (!list.isEmpty()) {
            list.clear();
        }
        ResultSet rs;
        if(data==null) {
            String sql = "SELECT * FROM dvd";
            rs = db.executeQuery(sql, null);
        }else {
            String sql = "SELECT * FROM dvd WHERE dvdno like ? OR dvdname like ? OR state like ?";
            Object[] params={"%"+data+"%","%"+data+"%","%"+data+"%"};
            rs = db.executeQuery(sql, params);
        }
            try {
                while (rs.next()) {
                    list.add(new DVD(rs.getInt("dvdno"), rs.getString("dvdname"), rs.getString("state"), rs.getString("picture")));
                }
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        //将当前页的值传给新的list_page集合中，list集合是全部数据综合，用i调用其中的几条数据给list_page
        if(!list_page.isEmpty()){
            list_page.clear();
        }
        for (int i = page*pageSize; i < (page + 1) *pageSize && i < list.size(); i++) {
            list_page.add(list.get(i));
        }
    }

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       request.setCharacterEncoding("UTF-8");
       //得到传过来的当前页
       String str_page=request.getParameter("page");

       //定义当前页数
       int page;
       count();
       if(str_page==null){
          page=0;
       }else{
          page=Integer.parseInt(str_page);
          if(page<0){
              page=0;
          }
          if(page>pageNumber){
              page=pageNumber;
          }
       }
       //得到传过来的搜索关键字
       String data=request.getParameter("selectDVD");

       dvdList(data,page);
       //创建session对象
       HttpSession session = request.getSession();

       //把用户数据保存在session域对象中
       session.setAttribute("listDVD", list_page);

       for(DVD dvd:list_page){
           dvd.getId();
           dvd.getDvdname();
           dvd.getState();
           dvd.getPicture();
       }
       //把最大页数保存在session域对象中
       session.setAttribute("pageNumber", pageNumber);

       //把当前页数保存在session域对象中
       session.setAttribute("page", page);

       nc();
       //把当前DVD已借出和可以借的数量保存在session域对象中
       session.setAttribute("cSize", cSize);
       session.setAttribute("ncSize", ncSize);

       response.sendRedirect("/fy/jsp/showdvd.jsp");
    }
}
