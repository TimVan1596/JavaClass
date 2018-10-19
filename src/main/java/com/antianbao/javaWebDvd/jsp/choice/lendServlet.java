package com.antianbao.javaWebDvd.jsp.choice;

import com.antianbao.javaWebDvd.dvd.Dvd;
import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "lendServlet",urlPatterns = {"/lend.do"})
public class lendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取输入信息
        int no = Integer.parseInt(request.getParameter("no"));
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
        List<Dvd> dvds = jdbcUtilDvd.queryStu();
        int jd = 0;
        for (Dvd dvd : dvds) {
            if (no == dvd.getNo() && dvd.getState()-dvd.getBorrow()>0) {
                jd = jdbcUtilDvd.updateState(dvd.getBorrow()+1, dvd.getNo());
                request.setAttribute("MSG", "借出成功！");
                request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
                break;
            }
        }
        if(jd == 0){
            request.setAttribute("MSG", "借出失败，库存数量不足！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }
    }
}
