package web.fy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "GetpasswordServlet",urlPatterns = {"/fy/toGetPassWord"})
public class GetpasswordServlet extends HttpServlet {
    DbUtil db = new DbUtil();
    RegisterServlet reg=new RegisterServlet();

    public String returnPass(String userName){
        String sql="SELECT * FROM user WHERE username=?";
        Object[] params={userName};
        ResultSet rs=db.executeQuery(sql,params);
        try {
            while (rs.next()) {
                return rs.getString("password");
            }
            db.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName=request.getParameter("username");

        if(!reg.check(userName)){
            String res=returnPass(userName);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<script language='javascript'>alert('你账号的密码为"+res+"');location.href='login.jsp';</script>");

        }else{
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('账号不存在');location.href='getpassword.jsp';</script>");
        }


    }
}
