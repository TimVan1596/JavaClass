package com.smallfangyu.servlet;

import com.smallfangyu.data.DVD;
import com.smallfangyu.data.DbUtil;
import com.smallfangyu.data.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowdvdServlet",urlPatterns = {"/fy/servlet/toShowDvd"})
public class ShowdvdServlet extends HttpServlet {
    static DbUtil db=new DbUtil();
    static Main ma=new Main();
    /**所有DVD集合
     *
     */
    static List<DVD> list=new ArrayList<DVD>();

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
    static int pageSize=5;

    /**
     * 定义一共的页数
     */
    static int pageNumber=0;

    /**
     * dvd数据总数
     */
    static int len = 0;

        /**
         *查询DVD已借出和可以借的数量
         */
        public void nc(){
        String sql = "SELECT COUNT(*) FROM dvd WHERE state=? AND `show`=1 ";
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
    public static void dvdList(String data) {
        len=0;
        if (!list.isEmpty()) {
            list.clear();
        }
        ResultSet rs;

       if(data==null){
            data="";
       }
//        String sql = "SELECT * FROM dvd WHERE `show`=1 AND (dvdno like ? OR dvdname like ? OR state like ?)";
//            Object[] params = {"%" + data + "%", "%" + data + "%", "%" + data + "%"};
//            rs = db.executeQuery(sql, params);
//        try {
//            while (rs.next()) {
//                list.add(new DVD(rs.getInt("dvdno"), rs.getString("dvdname"), rs.getString("state"), rs.getString("picture")));
        list=ma.getDvdList();
//                len++;
//            }
//            db.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        dvdLength = len;
        dvdLength=list.size();
    }

    public void updatepage(int page){
        if (dvdLength % pageSize == 0) {
            pageNumber = (dvdLength / pageSize) - 1;
        } else {
            pageNumber = dvdLength / pageSize;
        }
        //将当前页的值传给新的list_page集合中，list集合是全部数据综合，调用其中的几条数据给list_page
        if(!list_page.isEmpty()){
            list_page.clear();
        }
        for (int i = page*pageSize; i < (page + 1) *pageSize && i < list.size(); i++) {
            list_page.add(list.get(i));
        }
    }

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //request.setCharacterEncoding("UTF-8");
       //得到传过来的当前页
       String str_page=request.getParameter("page");
       //得到修改时传过来的当前页
       String str_pageX=request.getParameter("pageX");
       int pageX=0;
       if(str_pageX!=null){
           pageX=Integer.parseInt(str_pageX);
       }
       //得到传过来的搜索关键字
       String data=request.getParameter("selectDVD");
       //定义当前页数
       int page;
       int panduan;
       if(str_page==null){
           dvdList(data);
           panduan=0;
          page=0;
       }else{
           panduan=1;
           page=Integer.parseInt(str_page);
          if(page<0){
              page=0;
          }
          if(page>pageNumber){
              page=pageNumber;
          }
       }

       //根据查询的数据把数据添加进数组
if(panduan==0) {
    dvdList(data);
}
       //换页
       updatepage(page);

       //创建session对象
       HttpSession session = request.getSession();

       //把用户数据保存在session域对象中
       session.setAttribute("listDVD", list_page);

       //把最大页数保存在session域对象中
       session.setAttribute("pageNumber", pageNumber);


       if(str_pageX!=null){
           session.setAttribute("page",pageX);
           updatepage(pageX);
       }else{
           //把当前页数保存在session域对象中
           session.setAttribute("page", page);
       }
      //把dvd数据总数保存在session域对象中
       session.setAttribute("count", len);

       nc();
       //把当前DVD已借出和可以借的数量保存在session域对象中
       session.setAttribute("cSize", cSize);
       session.setAttribute("ncSize", ncSize);

       response.sendRedirect("/fy/jsp/showdvd.jsp");
    }
}
