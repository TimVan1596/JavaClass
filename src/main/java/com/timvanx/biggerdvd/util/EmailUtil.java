package com.timvanx.biggerdvd.util;

/**
 * @author TimVan
 * @date 2018/11/11 16:17
 */
import com.qiniu.util.Auth;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailUtil {
    private static final Logger LOGGER = Logger.getLogger(EmailUtil.class);

    private static final String HOSTNAME = "smtp.qq.com";
    /**
     * POP_USERNAME = 设置发送邮件的邮箱
     * USERNAME = 设置发件邮箱的显示名
     * POP_PASSWORD = 邮箱的授权码
     * */
    private static  String POP_USERNAME;
    private static  String USERNAME;
    private static  String POP_PASSWORD;
    private static final String CODING = "UTF-8";

    static {
        //初始化邮件发送信息(只做一次)
        init();
    }

    /**
     * 配置文件读取
     */
    private static void init(){

        //使用Properties读取配置文件
        // CONFIG_FILE 默认为 "ftmdb.properties"
        final String CONFIG_FILE = "ftmdb.properties";


        Properties properties = new Properties();
        InputStream in = JDBCUtil.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE);

        try {
            properties.load(in);
        } catch (IOException e) {
            Constants.reportError("邮箱信息配置文件");
            e.printStackTrace();
        }
        POP_USERNAME = properties.getProperty("POP_USERNAME");
        USERNAME = properties.getProperty("USERNAME");
        POP_PASSWORD = properties.getProperty("POP_PASSWORD");

    }

    /**
     * 普通文本邮件
     */
    public static void sendEmail(String toEmail,String emailSubject,String emailContent){
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setSSLOnConnect(true);
        simpleEmail.setSslSmtpPort("465");
		setEmailBase(simpleEmail);

        try {
            simpleEmail.addTo(toEmail);
            simpleEmail.setFrom(POP_USERNAME, USERNAME);
            simpleEmail.setSubject(emailSubject);
            simpleEmail.setMsg(emailContent);
            simpleEmail.send();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOGGER.error("发送邮件失败", e);
        }

    }

    /**
     * 普通文本邮件
     */
    public static void sendHtmlEmail(String[] toEmail,String emailSubject,String emailContent){
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setSSLOnConnect(true);
        htmlEmail.setSslSmtpPort("465");
		setEmailBase(htmlEmail);
        try {
            htmlEmail.addTo(toEmail);
            htmlEmail.setFrom(POP_USERNAME, USERNAME);
            htmlEmail.setSubject(emailSubject);
            htmlEmail.setHtmlMsg(emailContent);
            htmlEmail.send();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOGGER.error("发送邮件失败", e);
        }

    }

    public static void setEmailBase(Email email){
        email.setHostName(HOSTNAME);
        email.setAuthentication(POP_USERNAME, POP_PASSWORD);
        email.setCharset(CODING);
    }


    public static void main(String[] args) {
        String []sendAccount = new String[1];
        sendAccount[0] = "877020296@qq.com";

        String emailSubject = "【注册成功】欢迎使用 BiggerDVD";

        //sendAccount接收邮箱
		String emailID = sendAccount[0];
		String htmlMsg = "<html>\n" +
                "    <body>\n" +
                "        <div class=\"mailwrapper\" style=\"font-size: 15px; color: #666666;\">\n" +
                "        <table style=\"table-layout:fixed;width:100%;padding: 0; border: 0;\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><table align=\"center\" class=\"mainTable centerColumnWelcome\" style=\"margin:0 auto;font-size: inherit; line-height: inherit; border-collapse: collapse !important; border-spacing: 0; -premailer-cellpadding: 0; -premailer-cellspacing: 0; width: 800px; -premailer-width: 800; padding: 0px; border: 0px;\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                    <tbody><tr class=\"topPadding\" height=\"38\"><td style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; margin: 0; padding: 0px; border: 0px;\"></td></tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"centerColumnWelcome block1bg\" background=\"https://statici.icloud.com/emailimages/v4/familysharing/bkg_top_section.png\" style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; width: 800px; -premailer-width: 800; background-size: 100% 360px !important; background: url('https://statici.icloud.com/emailimages/v4/familysharing/bkg_top_section.png') no-repeat center bottom; margin: 0; padding: 0px; border: 0px; background-position: center bottom;\">\n" +
                "                            <table align=\"center\" style=\"margin: 0 auto; font-size: inherit; line-height: inherit; border-collapse: collapse !important; align: center; border-spacing: 0; padding: 0px; border: 0px;\"></table>\n" +
                "                        </td></tr><tr>\n" +
                "                        <td align=\"center\" style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; margin: 0; padding: 0px; border: 0px;\">\n" +
                "                            <img class=\"icloud_family_logo\" src=\"https://statici.icloud.com/emailimages/v4/accounts/welcome-2015/en_us/icloudwelcome_icloudlogo_2x.png\" style=\"display: block; border: 0px;\" width=\"146\" height=\"94\">\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"header2\" align=\"center\" style=\"font-family: 'SFNSText', 'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; color: #000000; font-size: 43px; line-height: 47px; margin: 0; padding: 23px 0px 0px; border: 0px;\">欢迎使用 BiggerDVD</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" class=\"header4\" style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; color: #797979 !important; font-size: 19px; line-height: 23px; margin: 0; padding: 10px 0px 10px; border: 0px;\">\n" +
                "                            您的 ID 是 <a style=\"color: #797979; text-decoration: none;\" rel=\"noopener\" target=\"_blank\">"+emailID+"\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"header4\" width=\"500\" align=\"center\" style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; color: #797979; font-size: 19px; line-height: 23px; margin: 0; padding: 10px 0px 0px; border: 0px;\">无论使用哪个设备，ID 都可以确保您随时访问 BiggerDVD 管理系统。</td>\n" +
                "                    </tr>\n" +
                "                    <tr class=\"blockPadding\" height=\"25\">\n" +
                "                        <td style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; margin: 0; padding: 0px; border: 0px;\"></td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align: center;\">\n" +
                "                            <button onclick=\"window.location.href='https://www.bihaiheifan.com/java/ftm/index.html'\" style=\"color: #fff;background-color: #007bff;border-color: #007bff;display: inline-block;font-weight: 400;text-align: center;white-space: nowrap;vertical-align: middle;-webkit-user-select: none;-moz-user-select: none;-ms-user-select: none;user-select: none;border: 1px solid transparent;padding: .375rem .75rem;font-size: 1rem;line-height: 1.5;border-radius: .25rem;transition: color .15s;cursor: pointer;\">立即登录</button></td></tr><tr></tr></tbody></table></td></tr></tbody></table><br><br>\n" +
                "        <table class=\"footerTable centerColumn\" align=\"center\" style=\"width: 800px; margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr>\n" +
                "                <td class=\"footer background centerColumn\" id=\"footerGroup1\" background=\"https://statici.icloud.com/emailimages/v4/common/footer_gradient_web.png\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; width: 685px; font-size: 11px; color: #888; text-align: center; background-repeat: no-repeat; background-position: center top; padding: 15px 0 10px;\" align=\"center\">\n" +
                "                    <img width=\"81\" height=\"22\" src=\"https://wx3.sinaimg.cn/mw690/0060lm7Tly1fx4b22dr4mj308n01gdfp.jpg\" style=\"display: inline-block; width: 85px; height: 15px;\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"footer centerColumn content\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased;  width: 685px; font-size: 11px; line-height: 14px; color: #888; text-align: center;\" align=\"center\">\n" +
                "         <span class=\"service\">\n" +
                "           BiggerDVD 是 TimVan 提供的服务。\n" +
                "         </span>\n" +
                "                    <span class=\"footer1\" style=\"white-space: nowrap;\">\n" +
                "           <a href=\"https://www.bihaiheifan.com/java/ftm/index.html\" style=\"color: #08c; text-decoration: none;\" rel=\"noopener\" target=\"_blank\">\n" +
                "             首页\n" +
                "           </a>\n" +
                "         </span> |\n" +
                "                    <span class=\"footer1\" style=\"white-space: nowrap;\">\n" +
                "           <a href=\"https://www.timvanx.com/\" style=\"color: #08c; text-decoration: none;\" rel=\"noopener\" target=\"_blank\">\n" +
                "             计协官网\n" +
                "           </a>\n" +
                "         </span>\n" +
                "                    <br>\n" +
                "                    <span class=\"bottomContent\">\n" +
                "           <span class=\"footer1\" style=\"white-space: nowrap;\">\n" +
                "             Copyright © 2018\n" +
                "           </span>\n" +
                "           <span>\n" +
                "             TimVan 范天明, <span class=\"nobr\"><a href=\"#address\" id=\"address\" style=\"color: #888888; text-decoration: none;cursor:text;\">People's Republic of China (PRC)\n" +
                ".</a></span>\n" +
                "           </span>\n" +
                "           <span class=\"footer1\" style=\"white-space: nowrap;\">\n" +
                "             保留所有权利。\n" +
                "           </span>\n" +
                "         </span>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr class=\"footerBottomPadding\" style=\"height: 50px; -premailer-height: 50;\" height=\"50\"><td style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased;\"></td></tr></tbody></table><table></table></div></body>\n" +
                "\n" +
                "</html>\n" +
                "\n";
    	sendHtmlEmail(sendAccount, emailSubject, htmlMsg);
    }
}