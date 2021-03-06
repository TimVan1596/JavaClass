package com.timvanx.biggerdvd.servlet.menu.adddvd;

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
 * 添加DVD信息
 * @author TimVan
 */
@WebServlet(name = "AddDVD",
        urlPatterns = {"/ftm/html/menu/adddvd/AddDVD.do"}, loadOnStartup = 1)
public class AddDVD extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //解决中文乱码
        response.setContentType("text/html;charset=utf-8");
        //请求解决乱码
        request.setCharacterEncoding("utf-8");
        //响应解决乱码
        response.setCharacterEncoding("utf-8");


        PrintWriter out = response.getWriter();

        String dvdName =  request.getParameter("name");
        String dvdPreview =  request.getParameter("preview");

        System.out.println("dvdName = "+dvdName);

        //数据中新增DVD信息
        DVD.addDVDInfo(dvdName,dvdPreview);

        Map<String, Object> ret = new HashMap<>(1);
        if (true) {
            ret.put("error", 0);
        } else {
            ret.put("error", 1);
            ret.put("errorInfo", "添加DVD信息出错");
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

}