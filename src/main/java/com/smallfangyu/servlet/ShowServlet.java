package com.smallfangyu.servlet;

import com.smallfangyu.data.DVD;
import com.smallfangyu.data.Olympic;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "ShowServlet",urlPatterns = {"/auto/show"})
public class ShowServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf8");
        //先定义一个Java对象
        String ol=request.getParameter("ol");
        String jin="jin";

        Olympic ol1=new Olympic("美国","http://www.sinaimg.cn/ty/08ay/data/logo/new/USA.jpg",46,37,38,121);
        Olympic ol2=new Olympic("英国","http://www.sinaimg.cn/ty/08ay/data/logo/new/GBR.jpg",27,23,17,67);
        Olympic ol3=new Olympic("中国","http://www.sinaimg.cn/ty/08ay/data/logo/new/CHN.jpg",26,18,26,70);
        Olympic ol4=new Olympic("俄罗斯","http://www.sinaimg.cn/ty/08ay/data/logo/new/RUS.jpg",19,18,19,56);
        Olympic ol5=new Olympic("德国","http://www.sinaimg.cn/ty/08ay/data/logo/new/GER.jpg",17,10,15,42);
        Olympic ol6=new Olympic("日本","http://www.sinaimg.cn/ty/08ay/data/logo/new/JPN.jpg",12,8,21,41);
        Olympic ol7=new Olympic("法国","http://www.sinaimg.cn/ty/08ay/data/logo/new/FRA.jpg",10,18,14,42);
        Olympic ol8=new Olympic("韩国","http://www.sinaimg.cn/ty/08ay/data/logo/new/KOR.jpg",9,3,9,21);
        Olympic ol9=new Olympic("意大利","http://www.sinaimg.cn/ty/08ay/data/logo/new/ITA.jpg",8,12,8,28);
        List<Olympic> ols=new ArrayList<Olympic>();
        ols.clear();
        ols.add(ol1);
        ols.add(ol2);
        ols.add(ol3);
        ols.add(ol4);
        ols.add(ol5);
        ols.add(ol6);
        ols.add(ol7);
        ols.add(ol8);
        ols.add(ol9);

//        if(ol.equals(jin)){
//            Collections.sort(ols);
//        }
        //String names[] =new String[dvds.size()];
        //这里可以把后台数据集 转为数组
        //把 集合 的数据装在数组中
//        for (int i=0;i<dvds.size();i++) {
//            names[i]=dvds.get(i).getDvdname();
//        }
        String json=JSONArray.fromObject(ols).toString();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", "100");
        result.put("data", json);

        response.getWriter().print(result);
    }
}
