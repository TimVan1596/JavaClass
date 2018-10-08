package web.ftm.servlet.menu;

import com.timvanx.biggerdvd.dvd.Account;

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

        String userName = request.getParameter("name");
        String userPassword = request.getParameter("password");
        System.out.println("name=" + userName + "   pwd=" + userPassword);

        //判断输入账户是否存在
        if (Account.login(userName, userPassword)) {
            out.write("true");
        } else {
            out.write("false");
        }

    }

}