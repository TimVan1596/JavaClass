package com.smallfangyu.attendance;

import com.smallfangyu.data.JdbcDruid;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddendanceServlet",urlPatterns = {"/fy/attendance"})
public class AddendanceServlet extends HttpServlet {
    static JdbcDruid db=new JdbcDruid();
   static ArrayList<Attendance> att=new ArrayList<>();


    public static void select(){
        if(att.size()!=0){
            att.clear();
        }
      String sql="SELECT * FROM people,attendance WHERE people.ano=attendance.ano";
      ResultSet res=db.executeQuery(sql,null);
      try {
          while (res.next()) {
            att.add(new Attendance(res.getInt("pno"),res.getString("name"),res.getString("entrytime"),res.getString("state"),res.getString("time")));
          }
          db.close();
      }catch(SQLException e){
          e.printStackTrace();
      }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        select();
        Map<String, Object> mjs = new HashMap<String, Object>();
        mjs.put("code",0);
        mjs.put("msg","");
        mjs.put("count",att.size());
        mjs.put("data",att);
        //把数据转化为json格式
        String json= JSONArray.fromObject(mjs).toString();
        //去掉第一个字符串和最后一个字符串
        json=json.substring(1,json.length()-1);
        response.getWriter().write(json);
    }

}
