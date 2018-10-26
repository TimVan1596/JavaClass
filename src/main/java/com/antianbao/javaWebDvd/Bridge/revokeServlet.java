package com.antianbao.javaWebDvd.Bridge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "revokeServlet",urlPatterns = {"/atbrevoke.do"})
public class revokeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        //request.setCharacterEncoding("utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        if(request.getParameter("page") != null){
            int page = Integer.parseInt(request.getParameter("page"));
            request.setAttribute("page", page);
        }
        request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
    }
}
