package com.smallfangyu.servlet;

import com.smallfangyu.data.DbUtil;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet(name = "GetpasswordServlet",urlPatterns = {"/servlet/toGetPassWord"})
public class GetpasswordServlet extends HttpServlet {
    DbUtil db = new DbUtil();
    RegisterServlet reg=new RegisterServlet();
   private String name;
   private String password;

    public String returnPass(String email){
        String sql="SELECT * FROM user WHERE username=?";
        Object[] params={email};
        ResultSet rs=db.executeQuery(sql,params);
        try {
            while (rs.next()) {
                name=rs.getString("username");
                return rs.getString("password");
            }
            db.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email=request.getParameter("email");

        password=returnPass(email);
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
            message.setSubject("找回密码");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>尊敬的用户，你好！</h1><h3>你的邮箱账号为"+name+"</h3><h3>密码为"+ password+"</h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('邮件发送成功，请注意查收！');location.href='/fy/jsp/getpassword.jsp';</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
