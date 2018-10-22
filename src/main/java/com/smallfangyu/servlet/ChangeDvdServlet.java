package com.smallfangyu.servlet;

import com.smallfangyu.data.DbUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet(name = "ChangeDvdServlet",urlPatterns = {"/fy/servlet/toChangeDvd"})
public class ChangeDvdServlet extends HttpServlet {
 DbUtil db=new DbUtil();
    public int change(int dvdId,String dvdName,String dvdState){
         String sql="UPDATE dvd SET dvdname=?,state=? WHERE dvdno=?";
         Object[] params={"《"+dvdName+"》",dvdState,dvdId};
         int res=db.executeUpdate(sql,params);
         return res;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int dvdId=Integer.parseInt(request.getParameter("dvdid"));
        String dvdName=request.getParameter("dvdname");
        String dvdState=request.getParameter("dvdstate");

        if(change(dvdId,dvdName,dvdState)>0){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('DVD修改成功');window.parent.location.href='/fy/servlet/toShowDvd';</script>");
        }else{
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('DVD修改失败')</script>");
        }

    }
}
