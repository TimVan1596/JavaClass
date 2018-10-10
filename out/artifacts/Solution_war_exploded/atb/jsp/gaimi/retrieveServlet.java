package web.atb.jsp.gaimi;

import web.atb.user.JDBCUtilUser;
import web.atb.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "retrieveServlet",urlPatterns = {"/atb/jsp/gaimi/Retrieve.do"})
public class retrieveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        if(name.equals("") || phone.equals("")){
            //跳转到登陆界面
            request.setAttribute("MSG", "请填写信息");
            request.getRequestDispatcher("/atb/jsp/gaimi/retrieve.jsp").forward(request, response);
        }else{
            JDBCUtilUser jdbcUtil = new JDBCUtilUser();
            int jd = jdbcUtil.isPhone(name, phone);
            if(jd > 0){
                //跳转到新的界面
                request.setAttribute("MSG", "验证成功！");
                request.setAttribute("name", name);
                request.getRequestDispatcher("/atb/jsp/gaimi/retrieveAction.jsp").forward(request, response);
            }else{
                //输出登陆失败
                request.setAttribute("MSG", "账号或手机号错误！");
                request.getRequestDispatcher("/atb/jsp/gaimi/retrieve.jsp").forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        password = new String(password.getBytes("ISO-8859-1"),"utf-8");
        cpassword = new String(cpassword.getBytes("ISO-8859-1"),"utf-8");
        if(name.equals("") || password.equals("") || cpassword.equals("")){
            //跳回注册界面
            request.setAttribute("MSG", "每项必填！");
            request.setAttribute("name", name);
            request.getRequestDispatcher("/atb/jsp/gaimi/retrieveAction.jsp").forward(request, response);
        }//判断两次密码是否相同
        else if(password.equals(cpassword)){
            JDBCUtilUser jdbcUtil = new JDBCUtilUser();
            int jd = jdbcUtil.updateStu(name, password);
            if(jd > 0){
                //跳转到登陆界面
                request.setAttribute("MSG", "修改成功！");
                request.getRequestDispatcher("/atb/login.jsp").forward(request, response);
            }else{
                //输出已账号存在,跳回注册界面
                request.setAttribute("MSG", "修改失败！");
                request.setAttribute("name", name);
                request.getRequestDispatcher("/atb/jsp/gaimi/retrieveAction.jsp").forward(request, response);
            }
        }else {
            //输出两次密码不一致,跳回注册界面
            request.setAttribute("MSG", "两次密码不一致！");
            request.setAttribute("name", name);
            request.getRequestDispatcher("/atb/jsp/gaimi/retrieveAction.jsp").forward(request, response);
        }
    }
}
