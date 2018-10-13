package web.atb.javaWebDvd.jsp.choice;

import web.atb.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "modifyServlet",urlPatterns = {"/modify.do"})
public class modifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        int no = Integer.parseInt(request.getParameter("no"));
        String name = request.getParameter("name");
        String state = request.getParameter("state");
        JDBCUtilDvd jdbcUtil = new JDBCUtilDvd();
        int jd = jdbcUtil.updateDvd(no,name,state);
        if(jd > 0){
            //修改成功跳转显示界面
            request.setAttribute("MSG", "修改成功！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }else{
            //输出修改失败
            request.setAttribute("MSG", "修改失败！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
