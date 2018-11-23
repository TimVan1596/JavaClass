package com.smallfangyu.attendance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smallfangyu.data.JdbcDruid;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddendanceServlet",urlPatterns = {"/fy/addendance"})
public class AddendanceServlet extends HttpServlet {
    static JdbcDruid db=new JdbcDruid();
   static ArrayList<Attendance> att=new ArrayList<>();


    public static void select(){
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
        //String json= JSONArray.fromObject(att).toString();
        Map<String, Object> mjs = new HashMap<String, Object>();
//        mjs.put("draw",1);
//        mjs.put("recordsTotal",57);
//        mjs.put("recordsFiltered",57);
        mjs.put("data",att);
        String json= JSONArray.fromObject(mjs).toString();
        response.getWriter().write(json);

    }
//    public static void main(String[] args){
//    select();
//    Map<String, Object> mjs = new HashMap<String, Object>();
//        mjs.put("draw",1);
//        mjs.put("recordsTotal",57);
//        mjs.put("recordsFiltered",57);
//        mjs.put("data",att);
//    String json= JSONArray.fromObject(mjs).toString();
//    System.out.println(json);
//   }
}
