package com.antianbao.text;

import org.apache.commons.mail.HtmlEmail;

public class Email {
    public static void main(String[] args) {
        try {
            //不用更改
            HtmlEmail email = new HtmlEmail();
            //需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setHostName("smtp.qq.com");
            email.setCharset("UTF-8");
            // 收件地址
            email.addTo("1287673695@qq.com");
            //此处填邮箱地址和用户名,用户名可以任意填写
            email.setFrom("32336077@qq.com", "DVD管理系统");
            //此处填写邮箱地址和客户端授权码
            email.setAuthentication("32336077@qq.com", "lhddogtibsurcbcc");
            //此处填写邮件名，邮件名可任意填写
            email.setSubject("管理员");
            //此处填写邮件内容
            int Code = (int)((Math.random()*9+1)*1000);
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + Code);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
