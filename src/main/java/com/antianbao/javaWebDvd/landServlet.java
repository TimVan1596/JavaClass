package com.antianbao.javaWebDvd;

import com.antianbao.javaWebDvd.user.JDBCUtilUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "landServlet",urlPatterns = {"/atbDvdLogin.do"})
public class landServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String userName = request.getParameter("username");
        String userPassword = request.getParameter("userpassword");
        if(userName.equals("绑定邮箱") || userPassword.equals("账号密码") || userName.equals("") || userPassword.equals("")){
            //跳转到新的界面
            request.setAttribute("MSG", "账号密码不能为空！");
            request.setAttribute("DL", "模板");
            request.getRequestDispatcher("./atb/javaWebDvdLogin.jsp").forward(request, response);
        }else{
            JDBCUtilUser jdbcUtil = new JDBCUtilUser();
            int jd = jdbcUtil.isReally(userName, userPassword);
            if(jd > 0){
                HttpSession session = request.getSession();
                //把用户数据保存在session域对象中
                session.setAttribute("loginName", userName);
                log4j.getInstance().getLogger().debug("用户名:" + session.getAttribute("loginName") + " 登录");
                request.setAttribute("MSG", "登陆成功！");
                //跳转到新的界面
                request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
            }else{
                //输出登陆失败
                request.setAttribute("MSG", "邮箱密码错误或用户不存在！");
                request.setAttribute("DL", "模板");
                request.getRequestDispatcher("./atb/javaWebDvdLogin.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
    }
}
