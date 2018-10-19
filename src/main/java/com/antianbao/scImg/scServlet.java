package com.antianbao.scImg;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

@WebServlet(name = "scServlet",urlPatterns = {"/sc.do"})
public class scServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //实例化一个硬盘文件工厂，用来配置文件组建ServletFileUpload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建一个文件上传解析器
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //乱码问题
        fileUpload.setHeaderEncoding("UTF-8");
        String path = this.getServletContext().getRealPath("/res/atbupload");
        File file = new File(path);//放在WEB-INF文件夹下，是因为比较安全

//            String name = request.getParameter("name");
//            String filename = "C:\\Users\\Administrator\\Desktop\\" + request.getParameter("img");
//            System.out.println(filename);
//            InputStream str = new FileInputStream(filename);
//            JDBCUtil jdbcUtil = new JDBCUtil();
//            int jd = jdbcUtil.addDvd(name,str);
//            if(jd > 0){
//                System.out.println(jd);
//            }else{
//                System.out.println(jd);
//            }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
    }
}
