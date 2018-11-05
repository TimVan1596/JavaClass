package com.antianbao.javaWebDvd.jsp.choice;

import org.apache.commons.mail.HtmlEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "emailServlet",urlPatterns = {"/atbEmail.do"})
public class emailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String userEmail = request.getParameter("userEmail");
        String yc = request.getParameter("yc");
        //正则表达式邮箱
        String regExp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        //手机号
        //String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(userEmail);
        if (m.matches()) {
            //跳转到登陆界面
            try {
                //不用更改
                HtmlEmail email = new HtmlEmail();
                //需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
                email.setHostName("smtp.qq.com");
                email.setCharset("UTF-8");
                // 收件地址
                email.addTo(userEmail);
                //此处填邮箱地址和用户名,用户名可以任意填写
                email.setFrom("32336077@qq.com", "DVD管理系统");
                //此处填写邮箱地址和客户端授权码
                email.setAuthentication("32336077@qq.com", "lhddogtibsurcbcc");
                //此处填写邮件名，邮件名可任意填写
                email.setSubject("安天宝");
                //此处填写邮件内容
                email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + yc);
                email.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            response.getWriter().print("<font color='yellow'>邮箱格式错误无法获取验证码</font>");
        }
    }
    /**
     * 判断手机号是否正确
     * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
    }
}
