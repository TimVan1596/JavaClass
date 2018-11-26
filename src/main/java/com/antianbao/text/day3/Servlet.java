package com.antianbao.text.day3;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Servlet",urlPatterns = {"/atb/attendance.do"})
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        //request.setCharacterEncoding("utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //阿里巴巴druid连接数据库
        JDBC jdbc = new JDBC();
        //遍历结果集
        List<attendance> att = jdbc.queryStu();
        Map<String, Object> mjs = new HashMap<String, Object>();
        mjs.put("code",0);
        mjs.put("msg","");
        mjs.put("count",att.size());
        mjs.put("data",att);
        //把数据转化为json格式
        String Json = JSON.toJSONString(att);
        response.getWriter().write(Json);
    }
}