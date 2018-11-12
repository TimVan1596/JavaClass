package com.smallfangyu.servlet;

import com.smallfangyu.data.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoanReturnServlet",urlPatterns = {"/fy/servlet/loanreturn"})
public class LoanReturnServlet extends HttpServlet {
    DbUtil db=new DbUtil();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idstate=request.getParameter("idstate");
        String page=request.getParameter("page");
        String[] ids=idstate.split(",");
        String state=ids[1];
        if(state.equals("可以借")){
            state="已借出";
        }else{
            state="可以借";
        }
        String sql = "UPDATE dvd SET state=? WHERE dvdno=?";
        Object[] params = {state,ids[0]};
        int res = db.executeUpdate(sql, params);
        if(res>0){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('操作成功');window.location.href='/fy/servlet/toShowDvd?pageX="+page+"';</script>");

            //location.href='/fy/servlet/toShowDvd';
        }
    }
}
