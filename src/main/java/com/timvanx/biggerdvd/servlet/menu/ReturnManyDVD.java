package com.timvanx.biggerdvd.servlet.menu;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.DVD;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 批量删除DVD信息
 * @author TimVan
 */
@WebServlet(name = "ReturnManyDVD",
        urlPatterns = {"/ftm/html/menu/ReturnManyDVD.do"}, loadOnStartup = 1)
public class ReturnManyDVD extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/text; charset=utf-8");
        PrintWriter out = response.getWriter();

        String dvdIDs =  request.getParameter("ids");

        Map<String, Object> ret = new HashMap<>(1);
        //归还DVD操作
        DVD.returnDVDInfoFromBin(dvdIDs);
        if (true) {
            ret.put("error", 0);
        }
        else {
            ret.put("error", 3);
            ret.put("errorInfo", "还原DVD信息出错");
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

}