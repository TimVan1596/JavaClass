package com.timvanx.biggerdvd.servlet.menu.statistics;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.DVD;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 获取DVD的统计信息
 * 返回json（总数，已借出数量）
 *
 * @author TimVan
 */
@WebServlet(name = "GetStatistics",
        urlPatterns = {"/ftm/html/menu/GetStatistics.do"},
        loadOnStartup = 1)
public class GetStatistics extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/text; charset=utf-8");
        PrintWriter out = response.getWriter();

        ArrayList<DVD> dvdArr = DVD.loadDVDInfosByArray();

        Map<String, Object> ret = new HashMap<>(1);
        if (true) {
            ret.put("error", 0);

            Map<String, Object> data = new HashMap<>(1);



            data.put("total",DVD.countDVDs());
            data.put("loaned",DVD.countLoanedDVDs());
            ret.put("data", data);
        } else {
            ret.put("error", 1);
            ret.put("errorInfo", "获取所有的DVD信息出错");
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

}