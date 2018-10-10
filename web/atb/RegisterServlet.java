package web.atb;

import web.atb.user.JDBCUtilUser;
import web.atb.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet",urlPatterns = {"/atb/Register.do"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        JDBCUtilUser jdbcUtil = new JDBCUtilUser();
        User user = new User(name, password, phone);
        int jd = jdbcUtil.addStu(user);
        if(jd > 0){
            //跳转到登陆界面
            response.sendRedirect("/atb/login.jsp");
        }else{
            //输出已账号存在,跳转注册界面
            request.setAttribute("MSG", "账号已存在！");
            response.sendRedirect("/atb/login.jsp");
            //request.getRequestDispatcher("/atb/register.jsp").forward(request, response);
            //response.sendRedirect("/atb/register.jsp");
        }

    }
}
