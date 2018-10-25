package com.antianbao.javaWebDvd.jsp.choice;

import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "deleteServlet",urlPatterns = {"/delete.do"})
public class deleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String[] check = request.getParameterValues("check");
        int jd = 0;
        if(check == null){
            request.setAttribute("MSG", "请选择要删除的图书！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }else{
            for(String c : check){
                JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
                jd = jdbcUtilDvd.deleteDvd(Integer.parseInt(c));
            }
        }
        //删除成功跳转显示界面
        if(jd > 0){
            request.setAttribute("MSG", "删除成功！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }

}
/**
 * 判断手机号是否正确
 * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        //1.取值
        String phone =request.getParameter("phone");
        phone = new String(phone.getBytes("ISO-8859-1"),"utf-8");
        //2.判断，此处用于判断是否已存在该用户
        /*
         * 13+任意数
         * 15+除4的任意数
         * 18+除1和4的任意数
         * 17+除9的任意数
         * 147
         */
        response.setContentType("text/html;charset=UTF-8");
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        //3.返回结果
        if(m.matches()) {
            response.getWriter().print("<font color='green'>手机号正确</font>");
        }else {
            response.getWriter().print("<font color='red'>手机号错误</font>");
        }
    }
}
