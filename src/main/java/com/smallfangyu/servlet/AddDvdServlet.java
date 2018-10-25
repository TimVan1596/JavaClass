package com.smallfangyu.servlet;

import com.smallfangyu.data.DbUtil;
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
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "AddDvdServlet",urlPatterns = {"/fy/servlet/toAddDvd"})
public class AddDvdServlet extends HttpServlet {
    DbUtil db=new DbUtil();

    public int addDvd(String dvdName){
        String sql="INSERT INTO dvd(dvdname,state) VALUES(?,?)";
        Object[] params={"《"+dvdName+"》","可以借"};
        int res=db.executeUpdate(sql,params);
        return res;
    }

    private void processUploadFile(FileItem item){
        String fileName=item.getName();
        String file=fileName.substring(fileName.lastIndexOf("\\")+1);
        File save=new File("e:/uploads/"+file);
        if(!save.isDirectory()) {
            try {
                FileUtils.copyInputStreamToFile(item.getInputStream(), save);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processFormField(FileItem item){
        String name=item.getFieldName();
        String value="";
        try{
            value=item.getString("UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        System.out.println(name+":"+value);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        boolean isMultipart= ServletFileUpload.isMultipartContent(request);
        if(isMultipart){
            DiskFileItemFactory factory=new DiskFileItemFactory();
            File repository=new File("e:/temps");
            factory.setRepository(repository);
            ServletFileUpload upload=new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setFileSizeMax(1028*1028*2);

            try {
                List<FileItem> items=upload.parseRequest(request);
                Iterator<FileItem> iter=items.iterator();
                while(iter.hasNext()){
                    FileItem item=iter.next();
                    if(item.isFormField()){
                        System.out.println("hahahha");
                         processFormField(item);
                    }else{
                         processUploadFile(item);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

        }

       // String dvdName=request.getParameter("dvdname");
        //if(addDvd(dvdName)>0){
            //response.setContentType("text/html;charset=UTF-8");
           // response.getWriter().write("<script language='javascript'>alert('DVD添加成功');window.parent.location.href='/fy/servlet/toShowDvd';</script>");

        //}

    }
}
