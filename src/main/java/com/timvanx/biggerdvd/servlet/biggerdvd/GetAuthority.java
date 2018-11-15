package com.timvanx.biggerdvd.servlet.biggerdvd;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.Account;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证登录界面的账户是否正确
 *
 * @author TimVan
 */
@WebServlet(name = "AccountCheckServlet",
        urlPatterns = {"/ftm/AccountCheckServlet.do"}, loadOnStartup = 1)
public class GetAuthority extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger logger  =  Logger.getLogger(GetAuthority.class );

        response.setContentType("application/text; charset=utf-8");
        PrintWriter out = response.getWriter();



        Map<String, Object> ret = new HashMap<>(1);

        //判断输入账户是否存在
        if (true) {
            HttpSession session = request.getSession();
            String authority = (String) session.getAttribute("authority");
            ret.put("error", 1);
            Map<String, Object> data = new HashMap<>(1);
            data.put("authority",authority);
            ret.put("data", data);

        } else {
            ret.put("error", 1);
            ret.put("errorInfo", "获取用户权限出错");
        }
        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

}