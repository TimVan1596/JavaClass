package web.fy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddDvdServlet",urlPatterns = {"/fy/toAddDvd"})
public class AddDvdServlet extends HttpServlet {
    DbUtil db=new DbUtil();
    public int addDvd(String dvdName){
        String sql="INSERT INTO dvd(dvdname,state) VALUES(?,?)";
        Object[] params={"《"+dvdName+"》","可以借"};
        int res=db.executeUpdate(sql,params);
        return res;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String dvdName=request.getParameter("dvdname");
        if(addDvd(dvdName)>0){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script language='javascript'>alert('DVD添加成功');window.parent.location.reload();</script>");
        }

    }
}