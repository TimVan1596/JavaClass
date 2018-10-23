package com.antianbao.javaWebDvd.jsp.choice;

import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "modifyServlet",urlPatterns = {"/modify.do"})
public class modifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        int no = Integer.parseInt(request.getParameter("no"));
        try{
            String name = request.getParameter("name");
            int state = Integer.parseInt(request.getParameter("state"));
            int borrow = Integer.parseInt(request.getParameter("borrow"));
            if(borrow > state){
                request.setAttribute("MSG", "借出数量不能大于总数！");
                request.getRequestDispatcher("./atb/javaWebDvd/jsp/choice/modify.jsp?no="+no+"").forward(request, response);
            }else{
                JDBCUtilDvd jdbcUtil = new JDBCUtilDvd();
                int jd = jdbcUtil.updateDvd(no,name,state,borrow);
                if(jd > 0){
                    //修改成功跳转显示界面
                    request.setAttribute("MSG", "修改成功！");
                    request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
                }else{
                    //输出修改失败
                    request.setAttribute("MSG", "修改失败！代码写错啦！");
                    request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
                }
            }
        }catch (NumberFormatException e){
            request.setAttribute("MSG", "总数和借出应为整数！");
            request.getRequestDispatcher("./atb/javaWebDvd/jsp/choice/modify.jsp?no="+no+"").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.取值
        String password =request.getParameter("password");
        String cpassword =request.getParameter("cpassword");
        password = new String(password.getBytes("ISO-8859-1"),"utf-8");
        cpassword = new String(cpassword.getBytes("ISO-8859-1"),"utf-8");
        //2.判断，此处用于判断是否已存在该用户
        response.setContentType("text/html;charset=UTF-8");
        //3.返回结果
        if(password.equals(cpassword)) {
            response.getWriter().print("<font color='green'>两次密码相同</font>");
        }else {
            response.getWriter().print("<font color='red'>两次密码不一致</font>");
        }
    }
}
