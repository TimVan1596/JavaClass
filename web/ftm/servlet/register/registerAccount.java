package web.ftm.servlet.register;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证登录界面的账户是否正确
 *
 * @author TimVan
 */
@WebServlet(name = "registerAccount",
        urlPatterns = {"/ftm/registerAccount.do"}, loadOnStartup = 1)
public class registerAccount extends HttpServlet {

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

        Map<String, Object> ret = new HashMap<>(1);

        //判断输入账户是否存在
        if (Account.register(userName, userPassword)) {
            ret.put("error", 0);
        } else {
            ret.put("error", 1);
            ret.put("errorInfo", "请检查用户名是否已存在");
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);


    }

}