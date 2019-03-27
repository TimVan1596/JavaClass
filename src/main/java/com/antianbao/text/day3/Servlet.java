package com.antianbao.text.day3;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "managementServlet",urlPatterns = {"/atb/attendance.do"})
public class Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        response.setContentType("application/text; charset=utf-8");
        //接收传过来的页数
        int page = Integer.parseInt(request.getParameter("page"));
        //接受传过来每页几行数据
        int limit = Integer.parseInt(request.getParameter("limit"));
        //接受需要搜索数据
        String no = request.getParameter("key[no]");
        String name = request.getParameter("key[name]");
        String date = request.getParameter("key[date]");
        // 获取入学日期范围
        String[] temp,day = new String[2];
        // 指定分割字符
        String symbol = " - ";
        if(date != null){
            // 分割字符串
            temp = date.split(symbol);
            // 普通 for 循环
            for(int i =0; i < temp.length ; i++){
                day[i] = temp[i];
            }
        }
        // 获取时间
        Date day1 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 查询数据前判断
        if(no == null){
            no="";
        }
        if(name == null){
            name="";
        }
        if(day[0] == null || day[1] == null){
            day[0] = "2010-1-1";
            day[1] = df.format(day1);
        }
        // 阿里巴巴druid连接数据库
        JDBC jdbc = new JDBC();
        // 遍历结果集
        List<attendance> att = jdbc.findPage(page,limit,no,name,day[0],day[1]);
        Map<String, Object> mjs = new HashMap<String, Object>();
        mjs.put("code",0);
        mjs.put("msg","");
        mjs.put("count",jdbc.findCountPage(no,name,day[0],day[1]));
        mjs.put("data",att);
        // 把数据转化为json格式
        String Json = JSON.toJSONString(mjs);
        System.out.println(Json);
        response.getWriter().write(Json);
    }
}