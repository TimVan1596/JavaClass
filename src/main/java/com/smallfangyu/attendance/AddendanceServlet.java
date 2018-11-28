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
    /**
     * 阿里巴巴druid连接数据库
     */
   static JdbcDruid db=new JdbcDruid();
   static ArrayList<Attendance> att=new ArrayList<>();

    /**
     * 按条件查询数据
     */
    public static void select(int page,int limit,String id,String state,String time){
        if(att.size()!=0){
            att.clear();
        }
        if(id==null){
            id="";
        }
        if(state==null){
            state="";
        }
        if(time==null){
            time="";
        }
      String sql="SELECT * FROM people,attendance WHERE people.ano=attendance.ano AND ((pno like ? OR name like ?)AND people.ano like ? AND time like ?) LIMIT ?,?";
      Object[] params={"%"+id+"%","%"+id+"%","%"+state+"%","%"+time+"%",(page-1)*limit,page*limit};
      ResultSet res=db.executeQuery(sql,params);
      try {
          while (res.next()) {
            att.add(new Attendance(res.getInt("pno"),res.getString("name"),res.getString("entrytime"),res.getString("state"),res.getString("time")));
          }
          db.close();
      }catch(SQLException e){
          e.printStackTrace();
      }
    }

    /**
     * 查询一共多少条数据
     * @return
     */
    public static int count(){
        int count=0;
        String sql="SELECT COUNT(*) FROM people";
        ResultSet res=db.executeQuery(sql,null);
        try {
            while (res.next()) {
              count=res.getInt("COUNT(*)");
            }
            db.close();
        }catch(SQLException e){
             e.printStackTrace();
        }
        return count;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //接收传过来的页数
        int page=Integer.parseInt(request.getParameter("page"));
        //接受传过来每页几行数据
        int limit=Integer.parseInt(request.getParameter("limit"));
        String pno=request.getParameter("id");
        String state=request.getParameter("state");
        String time=request.getParameter("time");
        //查数据
        select(page,limit,pno,state,time);
        Map<String, Object> mjs = new HashMap<String, Object>();
        mjs.put("code",0);
        mjs.put("msg","");
        mjs.put("count",count());
        mjs.put("data",att);
        //把数据转化为json格式
        String json= JSONArray.fromObject(mjs).toString();
        //去掉第一个字符串和最后一个字符串
        json=json.substring(1,json.length()-1);
        response.getWriter().write(json);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
