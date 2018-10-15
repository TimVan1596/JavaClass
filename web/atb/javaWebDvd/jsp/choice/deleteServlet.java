package web.atb.javaWebDvd.jsp.choice;

import web.atb.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "deleteServlet",urlPatterns = {"/delete.do"})
public class deleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String[] check = request.getParameterValues("check");
        int jd = 0;
        if(check == null){
            request.setAttribute("MSG", "请选择要删除的图书！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }else{
            for(String c : check){
                JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
                jd = jdbcUtilDvd.deleteDvd(Integer.parseInt(c));
            }
        }
        //删除成功跳转显示界面
        if(jd > 0){
            request.setAttribute("MSG", "删除成功！");
            request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
        }

}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
