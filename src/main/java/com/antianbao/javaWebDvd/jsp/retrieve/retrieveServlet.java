package com.antianbao.javaWebDvd.jsp.retrieve;

import com.antianbao.javaWebDvd.user.JDBCUtilUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "retrieveServlet",urlPatterns = {"/atbRetrieve.do"})
public class retrieveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        if(name.equals("用户账号") || phone.equals("绑定手机") || name.equals("") || phone.equals("")){
            //跳转到登陆界面
            request.setAttribute("MSG", "请填写信息");
            request.setAttribute("FH", "就是想返回");
            request.getRequestDispatcher("./atb/javaWebDvd/jsp/retrieve/retrieve.jsp").forward(request, response);
        }else{
            JDBCUtilUser jdbcUtil = new JDBCUtilUser();
            int jd = jdbcUtil.isPhone(name, phone);
            if(jd > 0){
                //跳转到新的界面
                request.setAttribute("MSG", "验证成功！");
                request.setAttribute("name", name);
                request.getRequestDispatcher("./atb/javaWebDvd/jsp/retrieve/retrieveAction.jsp").forward(request, response);
            }else{
                //输出登陆失败
                request.setAttribute("MSG", "账号或手机号错误！");
                request.setAttribute("FH", "就是想返回");
                request.getRequestDispatcher("./atb/javaWebDvd/jsp/retrieve/retrieve.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        password = new String(password.getBytes("ISO-8859-1"),"utf-8");
        cpassword = new String(cpassword.getBytes("ISO-8859-1"),"utf-8");
        if(name.equals("") || password.equals("") || cpassword.equals("")){
            //跳回注册界面
            request.setAttribute("MSG", "每项必填！");
            request.setAttribute("name", name);
            request.getRequestDispatcher("./atb/javaWebDvd/jsp/retrieve/retrieveAction.jsp").forward(request, response);
        }//判断两次密码是否相同
        else if(password.equals(cpassword)){
            JDBCUtilUser jdbcUtil = new JDBCUtilUser();
            int jd = jdbcUtil.updateStu(name, password);
            if(jd > 0){
                //跳转到登陆界面
                request.setAttribute("MSG", "修改成功！");
                request.setAttribute("DL", "意思下");
                request.getRequestDispatcher("./atb/javaWebDvdLogin.jsp").forward(request, response);
            }else{
                //输出已账号存在,跳回注册界面
                request.setAttribute("MSG", "修改失败！");
                request.setAttribute("name", name);
                request.getRequestDispatcher("./atb/javaWebDvd/jsp/retrieve/retrieveAction.jsp").forward(request, response);
            }
        }else {
            //输出两次密码不一致,跳回注册界面
            request.setAttribute("MSG", "两次密码不一致！");
            request.setAttribute("name", name);
            request.getRequestDispatcher("./atb/javaWebDvd/jsp/retrieve/retrieveAction.jsp").forward(request, response);
        }
    }
}
