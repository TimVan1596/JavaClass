package web.fy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "registerServlet",urlPatterns = {"/fy/toRegister"})
public class registerServlet extends HttpServlet {
    DbUtil db = new DbUtil();

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    public boolean check(String userName){
        String sql = "SELECT * FROM user ";
        ResultSet rs = db.executeQuery(sql, null);
        try {
            //判断用户名是否存在
            while (rs.next()) {
                if (!userName.equals(rs.getString("username"))) {
                    //遍历到rs的最后位置
                    if (rs.isLast()) {
                        //用户名可以注册
                        return true;
                    }
                } else {

                    break;
                }
            }
            //关闭资源
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int register(String userName,String passWord){
        String sql = "INSERT INTO user VALUES(?,?)";
        Object[] params={userName,passWord};
        int res = db.executeUpdate(sql, params);
        return res;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String userName=request.getParameter("username");
    String passWord=request.getParameter("password");

    if(check(userName)){
          int res=register(userName,passWord);
           if(res>0){
               response.setContentType("text/html;charset=UTF-8");
               response.getWriter().write("<script language='javascript'>alert('账号注册成功');location.href='login.jsp';</script>");
           }
    }else{
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().write("<script language='javascript'>alert('账号已被注册');location.href='register.jsp';</script>");

    }
    }
}
