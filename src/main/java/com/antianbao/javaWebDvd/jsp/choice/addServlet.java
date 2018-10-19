package com.antianbao.javaWebDvd.jsp.choice;

import com.antianbao.javaWebDvd.dvd.Dvd;
import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addServlet",urlPatterns = {"/add.do"})
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        try {
            String name = request.getParameter("name");
            int state = Integer.parseInt(request.getParameter("state"));
            JDBCUtilDvd jdbcUtil = new JDBCUtilDvd();
            Dvd dvd1 = new Dvd(name, state);
            int jd = jdbcUtil.addDvd(dvd1);
            if(jd > 0){
                //添加成功跳转显示界面
                request.setAttribute("MSG", "添加成功！");
                request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
            }else{
                //输出登陆失败
                request.setAttribute("MSG", "添加失败，该书已存在！");
                request.getRequestDispatcher("./atb/javaWebDvd/jsp/choice/add.jsp").forward(request, response);
            }
        }catch (NumberFormatException e){
            request.setAttribute("MSG", "库存应为整数！");
            request.getRequestDispatcher("./atb/javaWebDvd/jsp/choice/add.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        request.setAttribute("page", page);
        request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
    }
}
