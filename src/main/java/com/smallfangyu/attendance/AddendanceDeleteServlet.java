package com.smallfangyu.attendance;

import com.smallfangyu.data.JdbcDruid;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddendanceDeleteServlet",urlPatterns = {"/fy/addendanceDelete"})
public class AddendanceDeleteServlet extends HttpServlet {
    static JdbcDruid db=new JdbcDruid();

    /**
     * 批量删除
     * @param pno
     * @return
     */
    public static int attDelete(ArrayList<String> pno){
        int result=0;
        for(int i=0;i<pno.size();i++) {
            String sql = "DELETE FROM people WHERE pno=?";
            Object[] params = {pno.get(i)};
            result = db.executeUpdate(sql, params);
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String data=request.getParameter("dat");
     String[] dat=data.split(",");
     //取到传过来的pno值
        ArrayList<String> pno=new ArrayList<>();
        for(int i=2;i<dat.length;i+=5){
            pno.add(dat[i].substring(6));
        }
      if(attDelete(pno)>0){
          response.getWriter().write("200");
       }else{
          response.getWriter().write("110");
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
