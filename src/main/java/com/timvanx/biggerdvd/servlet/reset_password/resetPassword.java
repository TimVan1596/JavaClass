package com.timvanx.biggerdvd.servlet.reset_password;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 修改密码
 *
 * @author TimVan
 */
@WebServlet(name = "resetPassword",
        urlPatterns = {"/ftm/resetPassword.do"}, loadOnStartup = 1)
public class resetPassword extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/text; charset=utf-8");
        PrintWriter out = response.getWriter();
        String userEmail = request.getParameter("email");

        Map<String, Object> ret = new HashMap<>(1);

        if (!Account.changePasswordByName(userEmail, userEmail)) {
            ret.put("error", 1);
            ret.put("errorInfo", "用户名未找到！");
        } else {
            ret.put("error", 0);
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);

    }



}