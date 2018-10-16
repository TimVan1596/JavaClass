package web.fy.servlet;

import web.fy.data.DVD;
import web.fy.data.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SelectDvdServlet",urlPatterns ={"/fy/servlet/toSelectDvd"} )
public class SelectDvdServlet extends HttpServlet {
    static ArrayList<DVD> selectList =new ArrayList<DVD>();
    /**
     * 把搜索的数据库dvd表里的数据添加进数组里
     */
    public void selectdvdList(String data){
        if(!selectList.isEmpty()){
            selectList.clear();
        }
        DbUtil db=new DbUtil();
        String sql = "SELECT * FROM dvd WHERE dvdno like ? OR dvdname like ? OR state like ?";
        Object[] params={"%"+data+"%","%"+data+"%","%"+data+"%"};
        ResultSet rs = db.executeQuery(sql, params);

        try {
            while(rs.next()){
                selectList.add(new DVD(rs.getInt("dvdno"),rs.getString("dvdname"),rs.getString("state")));
            }

            db.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         request.setCharacterEncoding("UTF-8");
         String data=request.getParameter("selectDVD");
         selectdvdList(data);
         //创建session对象
         HttpSession session = request.getSession();
         //把用户数据保存在session域对象中
         session.setAttribute("selectlistDVD", selectList);
         response.sendRedirect("/fy/jsp/selectDVD.jsp");
    }
}
