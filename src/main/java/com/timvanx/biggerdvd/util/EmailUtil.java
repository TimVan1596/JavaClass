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

}