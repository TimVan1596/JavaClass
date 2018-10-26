package com.zhangmin.javawebDVDservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="showDVDServlet",urlPatterns = {"/zm/DVD/showDVD.do"})
public class showDVDServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
