package web.ftm.servlet.menu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 验证登录界面的账户是否正确
 *
 * @author TimVan
 */
public class AccountCheckServlet extends HttpServlet {

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

        String name = request.getParameter("name");
        String pwd = request.getParameter("password");
        System.out.println("name=" + name + " pwd=" + pwd);
        if (name.equals("admin") && pwd.equals("123456")) {
            try {
                out.write("true");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } else {
            try {
                out.write("false");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        }
    }

}