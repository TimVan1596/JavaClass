package com.antianbao.javaWebDvd.Bridge;

import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "revokeDeleteServlet",urlPatterns = {"/atbrevokeDelete.do"})
public class revokeDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //获取输入信息
        String[] check = request.getParameterValues("check");
        int jd = 0,image = 0;
        if(check == null){
            request.setAttribute("MSG", "请选择要清除的图书！");
            request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
        }else{
            for(String c : check){
                JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
                jd = jdbcUtilDvd.deleteRecovery(Integer.parseInt(c));
//                image = jdbcUtilDvd.deleteImage(Integer.parseInt(c));
            }
        }
        //删除成功跳转显示界面
//        if(image == 0){
//            request.setAttribute("MSG", "删除图片失败！");
//            request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
//        }
        if(jd > 0){
            request.setAttribute("MSG", "删除成功！");
            request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
        }else{
            request.setAttribute("MSG", "删除失败！");
            request.getRequestDispatcher("./atb/javaWebDvd/revoke.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");

    }
}

