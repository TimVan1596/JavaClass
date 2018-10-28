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
import java.util.ArrayList;

@WebServlet(name = "ShowBookServlet",urlPatterns = {"/book/showbook"})
public class ShowBookServlet extends HttpServlet {
    static DbUtil db=new DbUtil();
    /**所有Book集合
     *
     */
    ArrayList<Book> list=new ArrayList<Book>();

    /**当前页面Book集合
     *
     */
    ArrayList<Book> list_page = new ArrayList<Book>();


    /**
     * 定义dvd数据个数
     */
    static int dvdLength=0;

    /**
     * 定义一个页面多少条数据
     */
    static int pageSize=3;

    /**
     * 定义一共的页数
     */
    static int pageNumber=0;



    /**
     * 查询数据库一共几条数据
     */
    public void count() {
        String sq = "SELECT COUNT(*) FROM book ";
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
     * 把数据库Book表里的数据添加进数组里
     */
    public void dvdList(String data,int page) {
        if (!list.isEmpty()) {
            list.clear();
        }
        ResultSet rs;
        if(data==null) {
            String sql = "SELECT * FROM book,booktype WHERE book.typeid=booktype.typeid";
            rs = db.executeQuery(sql, null);
        }else {
            String sql = "SELECT * FROM book,booktype WHERE  book.typeid=booktype.typeid AND id like ? OR name like ? OR author like ?";
            Object[] params={"%"+data+"%","%"+data+"%","%"+data+"%"};
            rs = db.executeQuery(sql, params);
        }
        int len=0;
        try {
            while (rs.next()) {
                list.add(new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"),rs.getInt("price"), rs.getString("date"),rs.getString("typename")));
                len++;
            }
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dvdLength=len;

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
    request.setCharacterEncoding("UTF-8");
    //得到传过来的当前页
    String str_page=request.getParameter("page");
    //得到传过来的搜索关键字
    String data=request.getParameter("selectDVD");
    //定义当前页数
    int page;
    //count();

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
    dvdList(data,page);


    //创建session对象
    HttpSession session = request.getSession();

    //把用户数据保存在session域对象中
    session.setAttribute("listDVD", list_page);

    //把最大页数保存在session域对象中
    session.setAttribute("pageNumber", pageNumber);

    //把当前页数保存在session域对象中
    session.setAttribute("page", page);

    response.sendRedirect("/fy/book/jsp/showbook.jsp");
    }
}
