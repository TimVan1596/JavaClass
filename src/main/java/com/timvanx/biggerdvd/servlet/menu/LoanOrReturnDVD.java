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
 * 编辑的DVD的信息
 * @author TimVan
 */
@WebServlet(name = "LoanOrReturnDVD",
        urlPatterns = {"/ftm/html/menu/LoanOrReturnDVD.do"},
        loadOnStartup = 1)
public class LoanOrReturnDVD extends HttpServlet {

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

        String dvdID =  request.getParameter("id");
        int id = Integer.parseInt(dvdID);

        Map<String, Object> ret = new HashMap<>(1);
        //借出和归还DVD操作
        // 0 = 未找到  , 1 = 删除成功

        if (DVD.loanOrReturnDVDForWeb(id) == 1) {
            ret.put("error", 0);
        }
        else {
            ret.put("error", 1);
            ret.put("errorInfo", "请联系管理员");
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

}