package com.timvanx.biggerdvd.servlet.reset_password;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.timvanx.biggerdvd.util.EmailUtil.sendHtmlEmail;

/**
 * 验证验证码是否正确，并跳转到真正的修改密码页
 *
 * @author TimVan
 */
@WebServlet(name = "checkCapcha",
        urlPatterns = {"/ftm/checkCapcha.do"}, loadOnStartup = 1)
public class checkCapcha extends HttpServlet {

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
        String userCapcha = request.getParameter("capcha");

        Map<String, Object> ret = new HashMap<>(1);

        if (!Account.checkCAPCHA(userEmail,userCapcha)) {
            ret.put("error", 1);
            ret.put("errorInfo", "验证码输入错误");
        }
        else {
            ret.put("error", 0);
            HttpSession session = request.getSession();
            session.setAttribute("captchaEmail", userEmail);
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }


}