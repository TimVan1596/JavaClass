package com.antianbao.javaWebDvd.Bridge;

import com.antianbao.javaWebDvd.dvd.Dvd;
import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(name = "revokeReturnServlet",urlPatterns = {"/atbrevokeReturn.do"})
public class revokeReturnServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        //request.setCharacterEncoding("utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        int no = Integer.parseInt(request.getParameter("no"));
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
        List<Dvd> dvds = jdbcUtilDvd.recovery();
        int jd = 0;
        for (Dvd dvd : dvds) {
            if (no == dvd.getNo() && dvd.getBorrow() != 0 ) {
                jd = jdbcUtilDvd.recoveryState(dvd.getBorrow()-1, dvd.getNo());
                request.setAttribute("MSG", "归还成功！");
                request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
                break;
            }
        }
        if(jd == 0){
            request.setAttribute("MSG", "该书不缺哦，小老弟");
            request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
        }
    }
}
