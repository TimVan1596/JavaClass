package web.ftm.servlet.menu.display;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.DVD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 获取所有的DVD信息(JSTL版)
 * 返回 ArrayList<DVD> dvdArr
 *
 * @author TimVan
 */
@WebServlet(name = "GetAllDVDs4JSTL",
        urlPatterns = {"/ftm/html/menu/GetAllDVDs4JSTL"}, loadOnStartup = 1)
public class GetAllDVDs4JSTL extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/text; charset=utf-8");
        PrintWriter out = response.getWriter();

        //从DVD类DAO中获取List
        ArrayList<DVD> dvdArr = DVD.loadDVDInfosByArray();

        // 数据压入request容器，推向result.jsp
        request.setAttribute("dvdArr", dvdArr);
        try {
            request.getRequestDispatcher("../display.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }

}