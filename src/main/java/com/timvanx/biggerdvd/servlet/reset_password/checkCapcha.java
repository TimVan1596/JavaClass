package com.timvanx.biggerdvd.servlet.reset_password;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.timvanx.biggerdvd.util.EmailUtil.sendHtmlEmail;

/**
 * 验证验证码是否正确，并跳转到真正的修改密码页
 *
 * @author TimVan
 */
@WebServlet(name = "checkCapcha",
        urlPatterns = {"/ftm/checkCapcha.do"}, loadOnStartup = 1)
public class checkCapcha extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/text; charset=utf-8");
        PrintWriter out = response.getWriter();
        String userEmail = request.getParameter("email");
        String userCapcha = request.getParameter("capcha");

        Map<String, Object> ret = new HashMap<>(1);

        if (!Account.checkCAPCHA(userEmail,userCapcha)) {
            ret.put("error", 1);
            ret.put("errorInfo", "验证码输入错误");
        }
        else {
            ret.put("error", 0);
            HttpSession session = request.getSession();
            session.setAttribute("captchaEmail", userEmail);
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

    /**
     * 发送注册成功邮件
     * @param email 邮箱地址
     * @param CAPTCHA 验证码
     */
    private void sendEmail(String email,String CAPTCHA) {
        String []sendAccount = new String[1];
        sendAccount[0] = email;

        String emailSubject = "您正在使用验证码修改 BiggerDVD 的密码";

        //sendAccount接收邮箱
        String emailID = sendAccount[0];

        String htmlMsg = "<html><head><meta charset=\"utf-8\"></head><body><div class=\"mailwrapper\" style=\"font-size: 15px; color: #666666;\"><table style=\"table-layout:fixed;width:100%;padding: 0; border: 0;\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><table align=\"center\" class=\"mainTable centerColumnWelcome\"                       style=\"margin:0 auto;font-size: inherit; line-height: inherit; border-collapse: collapse !important; border-spacing: 0; -premailer-cellpadding: 0; -premailer-cellspacing: 0; width: 800px; -premailer-width: 800; padding: 0px; border: 0px;\"                       cellpadding=\"0\" cellspacing=\"0\"><tbody><tr class=\"topPadding\" height=\"38\"><td style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; margin: 0; padding: 0px; border: 0px;\"></td></tr><tr><td class=\"centerColumnWelcome block1bg\"                            background=\"https://statici.icloud.com/emailimages/v4/familysharing/bkg_top_section.png\"                            style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; width: 800px; -premailer-width: 800; background-size: 100% 360px !important; background: url('https://statici.icloud.com/emailimages/v4/familysharing/bkg_top_section.png') no-repeat center bottom; margin: 0; padding: 0px; border: 0px; background-position: center bottom;\"><table align=\"center\"                                   style=\"margin: 0 auto; font-size: inherit; line-height: inherit; border-collapse: collapse !important; align: center; border-spacing: 0; padding: 0px; border: 0px;\"></table></td></tr><tr><td align=\"center\"                            style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; margin: 0; padding: 0px; border: 0px;\"><img class=\"icloud_family_logo\"                                 src=\"https://statici.icloud.com/emailimages/v4/accounts/welcome-2015/en_us/icloudwelcome_icloudlogo_2x.png\"                                 style=\"display: block; border: 0px;\" width=\"146\" height=\"94\"></td></tr><tr><td class=\"header2\" align=\"center\"                            style=\"font-family: 'SFNSText', 'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; color: #000000; font-size: 43px; line-height: 47px; margin: 0; padding: 23px 0px 0px; border: 0px;\">                            注意！您正在修改 BiggerDVD 的密码</td></tr><tr><td align=\"center\" class=\"header4\"                            style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; color: #797979 !important; font-size: 19px; line-height: 23px; margin: 0; padding: 10px 0px 10px; border: 0px;\">                            您的 ID 是<a style=\"color: #797979; text-decoration: none;\" rel=\"noopener\" target=\"_blank\">"+emailID+"</a>。要完成修改密码，您将需要输入验证码：</td></tr><tr><td class=\"header4\" width=\"500\" align=\"center\" style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; color: #000000; font-size: 20px; line-height: 24px; margin: 0; padding: 10px 0px 0px; border: 0px;font-weight: bold;\">"+CAPTCHA+"</td></tr><tr><td align=\"center\" class=\"header4\" style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; color: #797979 !important; font-size: 14px; line-height: 23px; margin: 0; padding: 10px 0px 10px; border: 0px;\"><br><br>请不要将验证码告诉他人，确保您的帐户安全。</td></tr><tr class=\"blockPadding\" height=\"5\"><td style=\"font-family:'Helvetica Neue', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased; margin: 0; padding: 0px; border: 0px;\"></td></tr><tr></tr></tbody></table></td></tr></tbody></table><table class=\"footerTable centerColumn\" align=\"center\" style=\"width: 800px; margin: 0 auto;\" cellpadding=\"0\"           cellspacing=\"0\"><tbody><tr><td class=\"footer background centerColumn\" id=\"footerGroup1\"                background=\"https://statici.icloud.com/emailimages/v4/common/footer_gradient_web.png\"                style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; width: 685px; font-size: 11px; color: #888; text-align: center; background-repeat: no-repeat; background-position: center top; padding: 15px 0 10px;\"                align=\"center\"><img width=\"81\" height=\"22\" src=\"https://wx3.sinaimg.cn/mw690/0060lm7Tly1fx4b22dr4mj308n01gdfp.jpg\"                     style=\"display: inline-block; width: 85px; height: 15px;\"></td></tr><tr><td class=\"footer centerColumn content\"                style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased;  width: 685px; font-size: 11px; line-height: 14px; color: #888; text-align: center;\"                align=\"center\"><span class=\"service\">BiggerDVD 是 TimVan 提供的服务。</span><span class=\"footer1\"                                                                                           style=\"white-space: nowrap;\"><a                    href=\"https://www.bihaiheifan.com/java/ftm/index.html\" style=\"color: #08c; text-decoration: none;\"                    rel=\"noopener\" target=\"_blank\">首页</a></span> |<span class=\"footer1\" style=\"white-space: nowrap;\"><a                    href=\"https://www.timvanx.com/\" style=\"color: #08c; text-decoration: none;\" rel=\"noopener\"                    target=\"_blank\">计协官网</a></span><br><span class=\"bottomContent\"><span class=\"footer1\"                                                                                         style=\"white-space: nowrap;\">Copyright © 2018</span><span>TimVan 范天明,<span                    class=\"nobr\"><a href=\"#address\" id=\"address\"                                    style=\"color: #888888; text-decoration: none;cursor:text;\">People's Republic of China (PRC).</a></span></span><span class=\"footer1\" style=\"white-space: nowrap;\">保留所有权利。</span></span></td></tr><tr class=\"footerBottomPadding\" style=\"height: 50px; -premailer-height: 50;\" height=\"50\"><td style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased;\"></td></tr></tbody></table><table></table></div></body></html>\t\t\t";
        sendHtmlEmail(sendAccount, emailSubject, htmlMsg);
    }

}