package web.atb.javaWebDvd.jsp.choice;

import web.atb.javaWebDvd.dvd.Dvd;
import web.atb.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "lendServlet",urlPatterns = {"/lend.do"})
public class lendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取输入信息
        int no = Integer.parseInt(request.getParameter("no"));
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
        List<Dvd> dvds = jdbcUtilDvd.queryStu();
        for (Dvd dvd : dvds) {
            if (no == dvd.getNo() && dvd.getState().equals("可以借")) {
                jdbcUtilDvd.updateState("已借出", dvd.getNo());
                request.setAttribute("MSG", "借出成功！");
                request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
                break;
            }else if (no == dvd.getNo() && dvd.getState().equals("已借出")) {
                jdbcUtilDvd.updateState("可以借", dvd.getNo());
                request.setAttribute("MSG", "归还成功！");
                request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
                break;
            }
        }
    }
}
