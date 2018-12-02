package com.antianbao.fsLayui;

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

@WebServlet(name = "fsLayuiDisplayServlet",urlPatterns = {"/atb/fsLayuiDisplay.do"})
public class fsLayuiDisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // post解决中文乱码
        response.setContentType("application/text; charset=utf-8");
        // 获取前端传输的数据
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String id =  request.getParameter("id");
        String name =  request.getParameter("name");
        String createDate =  request.getParameter("createDate");
        String field =  request.getParameter("field");
        String order =  request.getParameter("order");
        // 获取入学日期范围
        String[] temp,day = new String[2];
        if(createDate != null){
            // 指定分割字符
            String symbol = " - ";
            // 分割字符串
            temp = createDate.split(symbol);
            // 普通 for 循环
            for(int i =0; i < temp.length ; i++){
                day[i] = temp[i];
            }
        }
        // 获取此刻时间
        Date day1 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(day[0] == null || day[1] == null){
            day[0] = "1600-1-1";
            day[1] = df.format(day1);
        }

        // 查询数据前判断
        if(id == null){
            id = "";
        }
        if(name == null){
            name = "";
        }
        if(field == null){
            field = "id";
        }
        if(order == null){
            order = "asc";
        }
        // 阿里巴巴druid连接数据库
        fsLayuiJDBC jdbc = new fsLayuiJDBC();
        // 遍历结果集 和 数据总数
//        List<fsLayuiUser> user = jdbc.test();
//        int totalNum = jdbc.countTest();
        List<fsLayuiUser> user = jdbc.findPage(pageNum,pageSize
                ,id,name,day[0],day[1],field,order);
        int totalNum = jdbc.findCountPage(id,name,day[0],day[1]);

        Map<String, Object> ret = new HashMap<>(1);
        ret.put("errorNo", 0);
        Map<String, Object> data = new HashMap<>(1);
        data.put("list",user);
        data.put("pageSize", pageSize);
        data.put("pages", pageNum);
        data.put("total", totalNum);
        Map<String, Object> results = new HashMap<>(1);
        results.put("data", data);

        ret.put("results", results);

        //使用 Alibaba fastJson 序列化 ret
        //p.s. 使用 Alibaba fastJson
        // 传输对象时需要 JavaBean 标准(Getter、Setter方法)
        String retJson = JSON.toJSONString(ret);
        response.getWriter().write(retJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}