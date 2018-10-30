package com.smallfangyu.servlet;

import com.smallfangyu.data.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReduceServlet",urlPatterns = {"/fy/reduce"})
public class ReduceServlet extends HttpServlet {

    DbUtil db=new DbUtil();
    public int reduce(String dvdId){
        String sql="UPDATE dvd SET `show`=1 WHERE dvdno=?";
        Object[] params={dvdId};
        int res=db.executeUpdate(sql,params);
        return res;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String dvdId=request.getParameter("id");
      if(reduce(dvdId)>0){
          response.getWriter().write("<script language='javascript'>alert('DVD还原成功');window.location.href='/fy/recycle';</script>");
      }else{
          //response.setContentType("text/html;charset=UTF-8");
          response.getWriter().write("<script language='javascript'>alert('DVD还原失败')</script>");
      }
    }
}
