package com.timvanx.biggerdvd.servlet.menu.display;

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
 * 获取所有的DVD信息
 * 返回json
 *
 * @author TimVan
 */
@WebServlet(name = "GetAllDVDs",
        urlPatterns = {"/ftm/html/menu/GetAllDVDs.do"}, loadOnStartup = 1)
public class GetAllDVDs extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/text; charset=utf-8");
        PrintWriter out = response.getWriter();

        String pageNumStr =  request.getParameter("pageNum");
        String pageSizeStr =  request.getParameter("pageSize");
        String queryStr =  request.getParameter("query");

        int pageNum = Integer.valueOf(pageNumStr);
        int pageSize = Integer.valueOf(pageSizeStr);

        int total = 1;

        String tableWhere = null;
        if (!queryStr.equals("")){
            tableWhere = "name like '%" +queryStr+"%' ";
            tableWhere += "or id like '%" +queryStr+"%' ";
        }

        ArrayList<DVD> dvdArr = DVD
                .loadDVDInfosByArray(pageNum,pageSize,tableWhere);
        int totalNum = DVD.countDVDs(tableWhere);

        total += totalNum/pageSize;
        if (totalNum - pageSize*total > 0){
            total += 1;
        }

        Map<String, Object> ret = new HashMap<>(1);
        if (true) {
            ret.put("error", 0);
            //构建Json返回值
            Map<String, Object> data = new HashMap<>(1);
            data.put("list",dvdArr);
            data.put("total",total);
            ret.put("data", data);
        } else {
            ret.put("error", 1);
            ret.put("errorInfo", "获取所有的DVD信息出错");
        }

        //使用 Alibaba fastJson 序列化 ret
        //p.s. 使用 Alibaba fastJson
        // 传输对象时需要 JavaBean 标准(Getter、Setter方法)
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

}