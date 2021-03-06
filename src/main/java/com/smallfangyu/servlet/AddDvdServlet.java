package com.smallfangyu.servlet;


import com.smallfangyu.data.DbUtil;
import com.smallfangyu.data.JdbcDruid;
import com.smallfangyu.data.Main;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(name = "AddDvdServlet",urlPatterns = {"/fy/servlet/toAddDvd"})
public class AddDvdServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    String dvdname = null;
    String photo = null;
    //jdbc类
    //DbUtil db = new DbUtil();
    //阿里巴巴druid连接数据库
    JdbcDruid db=new JdbcDruid();
    //mybatis操作类
    Main ma=new Main();

    public int addDvd(String dvdName, String photo) {
        String sql = "INSERT INTO dvd(dvdname,state,picture) VALUES(?,?,?)";
        Object[] params = {"《" + dvdName + "》", "可以借", photo};
        int res = db.executeUpdate(sql, params);
        return res;
    }

    public String processUploadFile(FileItem item) {
        String fileName = item.getName();
        if (fileName.length() == 0) {
            return "http://wx3.sinaimg.cn/mw690/0060lm7Tly1fwc6vyd1c1j30850643yj.jpg";
        } else {
            String newPicName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            File save = new File("F:/IdeaProjects/java_direction_class/web/fy/uploads/" + newPicName);
            if (!save.isDirectory()) {
                try {
                    FileUtils.copyInputStreamToFile(item.getInputStream(), save);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "../uploads/" + newPicName;
        }
    }

    public String processFormField(FileItem item){
        String value="";
        try{
            value=item.getString("UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return value;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
      }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         boolean isMultipart= ServletFileUpload.isMultipartContent(request);
         if(isMultipart){
             DiskFileItemFactory factory=new DiskFileItemFactory();
             File repository=new File("F:/IdeaProjects/java_direction_class/web/fy/temps/");
             if(!repository.exists()){
                 repository.mkdirs();
             }
             factory.setRepository(repository);
             ServletFileUpload upload=new ServletFileUpload(factory);
             upload.setHeaderEncoding("UTF-8");
             upload.setFileSizeMax(1028*1028*2);

             try {
                 List<FileItem> items = upload.parseRequest(request);
                 Iterator<FileItem> iter=items.iterator();
                 while(iter.hasNext()){
                     FileItem item=iter.next();
                     if(item.isFormField()){
                         dvdname=processFormField(item);
                     }else{
                         photo=processUploadFile(item);
                     }
                 }
             } catch (FileUploadException e) {
                 e.printStackTrace();
             }
         }else{
         }

         //jdbc添加
        if(addDvd(dvdname,photo)>0){

        //mybatis映射添加
        //if(ma.dvdInsert(dvdname,photo)>0){
         response.getWriter().write("<script language='javascript'>alert('DVD添加成功');window.parent.location.href='/fy/servlet/toShowDvd';</script>");
        }
    }

}
