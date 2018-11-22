package com.smallfangyu.servlet;

import com.smallfangyu.data.DbUtil;
import com.smallfangyu.data.Main;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet(name = "ChangeDvdServlet",urlPatterns = {"/fy/servlet/toChangeDvd"})
public class ChangeDvdServlet extends HttpServlet {
 private String photo;
 DbUtil db=new DbUtil();
 Main ma=new Main();
 AddDvdServlet ad=new AddDvdServlet();
 ArrayList<String> text=new ArrayList<String>();

    public int change(String dvdId,String dvdName,String dvdState,String photo){
        String sql;
        int res;
        if(photo.length()==0) {
            sql= "UPDATE dvd SET dvdname=?,state=? WHERE dvdno=?";
            Object[] params= {"《" + dvdName + "》", dvdState, dvdId};
            res = db.executeUpdate(sql, params);
        }else {
            sql = "UPDATE dvd SET dvdname=?,state=?,picture=? WHERE dvdno=?";
            Object[] params ={"《" + dvdName + "》", dvdState, photo, dvdId};
            res = db.executeUpdate(sql, params);
        }
            text.clear();
            return res;
    }

    public String processUploadFile(FileItem item) {
        String fileName = item.getName();
        if (fileName.length() == 0) {
            return "";
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
                text.clear();
                while(iter.hasNext()){
                    FileItem item=iter.next();
                    if(item.isFormField()){
                        //集合里循环添加text里的数据
                        text.add(ad.processFormField(item));
                    }else{
                        photo=processUploadFile(item);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }else{
        }
        int a=Integer.parseInt(text.get(0));

        //jdbc修改
        if(change(text.get(0),text.get(1),text.get(2),photo)>0){

        //mybatis映射修改
        //if(ma.dvdUpdate(a,text.get(1),text.get(2),photo)>0){
            response.getWriter().write("<script language='javascript'>alert('DVD修改成功');window.parent.location.href='/fy/servlet/toShowDvd';</script>");
        }else{
            response.getWriter().write("<script language='javascript'>alert('DVD修改失败')</script>");
            }
    }
}
