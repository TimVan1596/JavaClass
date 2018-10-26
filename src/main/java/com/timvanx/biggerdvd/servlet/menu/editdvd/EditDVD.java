package com.timvanx.biggerdvd.servlet.menu.editdvd;

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
@WebServlet(name = "EditDVD",
        urlPatterns = {"/ftm/html/menu/editdvd/EditDVD.do"}, loadOnStartup = 1)
public class EditDVD extends HttpServlet {

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
        String dvdID =  request.getParameter("id");
        int id = Integer.parseInt(dvdID);
        String dvdPreview =  request.getParameter("preview");

        System.out.println("dvdPreview = "+dvdPreview);


        Map<String, Object> ret = new HashMap<>(1);
        //数据中编辑DVD信息
        if (DVD.editDVDInfo(id,dvdName,dvdPreview)) {
            ret.put("error", 0);
        } else {
            ret.put("error", 1);
            ret.put("errorInfo", "编辑DVD信息出错");
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

}