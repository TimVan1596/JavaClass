package com.smallfangyu.bookjavaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateBookServlet",urlPatterns = {"/book/updatebook"})
public class UpdateBookServlet extends HttpServlet {
    DbUtil db=new DbUtil();
    public int change(int id,String name,String author,String price,String date,String type){
        String sql="UPDATE book SET name=?,author=?,price=?,date=?,typeid=? WHERE id=?";
        Object[] params={name,author,price,date,type,id};
        int res=db.executeUpdate(sql,params);
        return res;
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    int id=Integer.parseInt(request.getParameter("id"));
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
    if(change(id,name,author,price,date,type)>0){
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script language='javascript'>alert('DVD修改成功');window.location.href='/book/showbook';</script>");
    }else{
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script language='javascript'>alert('DVD修改失败')</script>");
    }
    }
}
