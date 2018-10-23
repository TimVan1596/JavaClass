package com.antianbao.javaWebDvd.jsp.choice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "pagingServlet",urlPatterns = {"/paging.do"})
public class pagingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        int page = Integer.parseInt(request.getParameter("page"));
        String search = request.getParameter("search");
        search = new String(search.getBytes("ISO-8859-1"),"utf-8");
        request.setAttribute("page", page);
        request.setAttribute("search", search);
        request.getRequestDispatcher("./atb/javaWebDvd/paging.jsp").forward(request, response);
    }
}
