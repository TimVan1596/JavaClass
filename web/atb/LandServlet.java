package web.atb;

import web.atb.user.JDBCUtilUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LandServlet",urlPatterns = {"/atb/login.do"})
public class LandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("啊哈哈哈");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String userPassword = request.getParameter("userpassword");
        JDBCUtilUser jdbcUtil = new JDBCUtilUser();
        int jd = jdbcUtil.isReally(userName, userPassword);
        if(jd > 0){
            //跳转到新的界面
            response.sendRedirect("/atb/main.jsp");
        }else{
            //输出登陆失败
            response.getWriter().write("账号或密码错误！");
        }
    }
}
