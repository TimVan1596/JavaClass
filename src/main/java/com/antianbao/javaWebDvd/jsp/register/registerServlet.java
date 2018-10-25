package com.antianbao.javaWebDvd.jsp.register;

import com.antianbao.javaWebDvd.user.JDBCUtilUser;
import com.antianbao.javaWebDvd.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "registerServlet",urlPatterns = {"/atbRegister.do"})
public class registerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        String phone = request.getParameter("phone");
        //每项必填
        if(name.equals("用户账号") || phone.equals("绑定手机") || password.equals("账号密码") || cpassword.equals("确认密码")
                || name.equals("") || phone.equals("") || password.equals("") || cpassword.equals("")){
            //跳回注册界面
            request.setAttribute("MSG", "每项必填！");
            request.setAttribute("FH", "就是想返回");
            request.getRequestDispatcher("./atb/javaWebDvd/jsp/register/register.jsp").forward(request, response);
        }//判断两次密码是否相同
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        //3.返回结果
        if(m.matches()) {
            if(password.equals(cpassword)){
                JDBCUtilUser jdbcUtil = new JDBCUtilUser();
                User user = new User(name, password, phone);
                int jd = jdbcUtil.addStu(user);
                if(jd > 0){
                    //跳转到登陆界面
                    request.setAttribute("MSG", "注册成功！");
                    request.setAttribute("DL", "意思下");
                    request.getRequestDispatcher("./atb/javaWebDvdLogin.jsp").forward(request, response);
                }else{
                    //输出已账号存在,跳回注册界面
                    request.setAttribute("MSG", "账号已存在注册失败！");
                    request.setAttribute("FH", "就是想返回");
                    request.getRequestDispatcher("./atb/javaWebDvd/jsp/register/register.jsp").forward(request, response);
                }
            }else {
                //输出两次密码不一致,跳回注册界面
                request.setAttribute("MSG", "两次密码不一致！");
                request.setAttribute("FH", "就是想返回");
                request.getRequestDispatcher("./atb/javaWebDvd/jsp/register/register.jsp").forward(request, response);
            }
        }else {
            //输出两次密码不一致,跳回注册界面
            request.setAttribute("MSG", "手机号不正确！");
            request.setAttribute("FH", "就是想返回");
            request.getRequestDispatcher("./atb/javaWebDvd/jsp/register/register.jsp").forward(request, response);
        }

    }
    /**
     * 判断账号是否存在
     * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        //1.取值
        String name =request.getParameter("name");
        name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        //2.判断，此处用于判断是否已存在该用户
        JDBCUtilUser userService = new JDBCUtilUser();
        int isExist = userService.panduan(name);
        response.setContentType("text/html;charset=UTF-8");
        //3.返回结果
        if(isExist == 1) {
            response.getWriter().print("<font color='red'>该账号已存在</font>");
        }else {
            response.getWriter().print("<font color='green'>恭喜您账号可用</font>");
        }
    }
}
