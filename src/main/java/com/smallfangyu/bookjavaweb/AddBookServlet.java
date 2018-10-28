package com.smallfangyu.bookjavaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddBookServlet",urlPatterns = {"/book/addbook"})
public class AddBookServlet extends HttpServlet {
    DbUtil db = new DbUtil();

    public int addBook(String name,String author,String price,String date,String type) {
        String sql = "INSERT INTO book(name,author,price,date,typeid) VALUES(?,?,?,?,?)";
        Object[] params = {name, author,price,date,type};
        int res = db.executeUpdate(sql, params);
        return res;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name=request.getParameter("name");
        String author=request.getParameter("author");
        String price=request.getParameter("price");
        String date=request.getParameter("date");
        String type=request.getParameter("type");
        if(type.equals("玄幻")){
            type="2";
        }else if(type.equals("仙侠")){
            type="1";
        }else{
            type="3";
        }
        if(addBook(name,author,price,date,type)>0){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('BOOK添加成功');window.location.href='/book/showbook';</script>");
        }else{
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('BOOK添加失败')</script>");
        }
    }
}
