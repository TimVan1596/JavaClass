package com.antianbao.webtest;

import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "deleteServlet",urlPatterns = {"/atbdelete.do"})
public class deleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String[] check = request.getParameterValues("check");
        int jd = 0,zc = 0;
        if(check == null){
            request.setAttribute("MSG", "请选择要删除的图书！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }else{
            for(String c : check){
                JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
                zc = jdbcUtilDvd.recoveryAddDvd(Integer.parseInt(c));
                jd = jdbcUtilDvd.deleteDvd(Integer.parseInt(c));
            }
        }
        //删除成功跳转显示界面
        if(jd > 0){
            request.setAttribute("MSG", "删除成功！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }
        if(zc == 0){
            request.setAttribute("MSG", "转存失败！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }
    }
    /**
     * 判断手机号是否正确
     * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        String name =request.getParameter("name");
        name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(name);
        response.setContentType("text/html;charset=UTF-8");
        //2.返回结果
        if(!m.matches()) {
            response.getWriter().print("<font color='yellow'>邮箱格式错误</font>");
        }
//        /*
//         * 13+任意数
//         * 15+除4的任意数
//         * 18+除1和4的任意数
//         * 17+除9的任意数
//         * 147
//         */
//        response.setContentType("text/html;charset=UTF-8");
//        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
//        Pattern p = Pattern.compile(regExp);
//        Matcher m = p.matcher(phone);
//        //2.返回结果
//        if(m.matches()) {
//            response.getWriter().print("<font color='green'>手机号正确</font>");
//        }else {
//            response.getWriter().print("<font color='yellow'>手机号错误</font>");
//        }
    }
}
