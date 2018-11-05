package com.smallfangyu.servlet;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

@WebServlet(name = "CodeServlet",urlPatterns = {"/fy/servlet/code"})
public class CodeServlet extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email=request.getParameter("username");

    if(email.length()==0){
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script language='javascript'>alert('请输入邮箱！');location.href='/fy/jsp/register.jsp';</script>");
    }

    int atpos = email.indexOf("@");
    int dotpos = email.lastIndexOf(".");

    if(atpos<1 || dotpos<atpos + 2 ) {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script language='javascript'>alert('邮箱格式不可用！');location.href='/fy/jsp/register.jsp';</script>");
        }
    // 1.创建连接对象javax.mail.Session
    // 2.创建邮件对象 javax.mail.Message
    // 3.发送一封激活邮件
    String from = "928247992@qq.com";
    // 发件人电子邮箱
    String host = "smtp.qq.com";
    // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)

    Properties properties = System.getProperties();
    // 获取系统属性

    properties.setProperty("mail.smtp.host", host);
    // 设置邮件服务器
    properties.setProperty("mail.smtp.auth", "true");
    // 打开认证

    try {
        //QQ邮箱需要下面这段代码，163邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);


        // 1.获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("928247992@qq.com", "bowmgbusuvwubfeg");
                // 发件人邮箱账号、授权码
            }
        });

        // 2.创建邮件对象
        Message message = new MimeMessage(session);
        // 2.1设置发件人
        message.setFrom(new InternetAddress(from));
        // 2.2设置接收人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // 2.3设置邮件主题
        message.setSubject("注册账号");
        // 2.4设置邮件内容
        String code= UUID.randomUUID().toString().substring(0,4);
        //创建session对象
        HttpSession session2 = request.getSession();
        //把验证码数据保存在session域对象中
        session2.setAttribute("code", code);

        String content = "<html><head></head><body><h1>尊敬的用户，你好！</h1><h2>你正在注册DVD管理系统账号</h2><h3>验证码为："+code+"</h3></body></html>";
        message.setContent(content, "text/html;charset=UTF-8");
        // 3.发送邮件
        Transport.send(message);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('邮件发送成功，请注意查收！');location.href='/fy/jsp/register.jsp';</script>");

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
