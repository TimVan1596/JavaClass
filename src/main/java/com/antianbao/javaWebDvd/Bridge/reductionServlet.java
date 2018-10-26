package com.antianbao.javaWebDvd.Bridge;

import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "reductionServlet",urlPatterns = {"/atbreduction.do"})
public class reductionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        //request.setCharacterEncoding("utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        //获取输入信息
        int no = Integer.parseInt(request.getParameter("no"));
        int jd = 0,zc = 0;
        JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
        zc = jdbcUtilDvd.reductionAddDvd(no);
        jd = jdbcUtilDvd.deleteRecovery(no);
        //删除成功跳转显示界面
        if(jd > 0){
            request.setAttribute("MSG", "恢复成功！");
            request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
        }
        if(zc == 0){
            request.setAttribute("MSG", "转存失败！");
            request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
        }
    }
}
