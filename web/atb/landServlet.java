package web.atb;

import web.atb.user.JDBCUtilUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "landServlet",urlPatterns = {"/atb/login.do"})
public class landServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String userName = request.getParameter("username");
        String userPassword = request.getParameter("userpassword");
        if(userName.equals("") || userPassword.equals("")){
            //跳转到新的界面
            request.setAttribute("MSG", "账号密码不能为空！");
            request.getRequestDispatcher("./login.jsp").forward(request, response);
        }else{
            JDBCUtilUser jdbcUtil = new JDBCUtilUser();
            int jd = jdbcUtil.isReally(userName, userPassword);
            if(jd > 0){
                //跳转到新的界面
                request.setAttribute("MSG", "登陆成功！");
                request.getRequestDispatcher("./choice.jsp").forward(request, response);
            }else{
                //输出登陆失败
                request.setAttribute("MSG", "账号或密码错误！");
                request.getRequestDispatcher("./login.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
    }
}
