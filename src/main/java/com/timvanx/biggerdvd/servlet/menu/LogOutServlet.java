package com.timvanx.biggerdvd.servlet.menu;


import com.timvanx.biggerdvd.util.CommonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 注销页面
 *
 * @author TimVan
 */
@WebServlet(name = "LogOutServlet",
        urlPatterns = {"/ftm/LogOutServlet.do"}, loadOnStartup = 1)
public class LogOutServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("username");
        }
        CommonUtil.popAlert(out, "注销成功"
                , "index.html");

        return;
    }

}