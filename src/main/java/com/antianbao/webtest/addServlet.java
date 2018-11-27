package com.antianbao.webtest;

import com.antianbao.javaWebDvd.dvd.Dvd;
import com.antianbao.javaWebDvd.dvd.JDBCUtilDvd;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "addServlet",urlPatterns = {"/atbadd.do"})
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart){
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决中文乱码
            upload.setHeaderEncoding("utf-8");
            // Parse the request
            try {
                List<String> list = new ArrayList<>();
                List<FileItem> items = upload.parseRequest(request);
                //处理参数
                for(FileItem item:items){
                    if(item.getString("utf-8").equals("")){
                        //添加界面
                        request.setAttribute("MSG", "每项内容不得为空!");
                        request.getRequestDispatcher("./atb/javaWebDvd/jsp/choice/add.jsp").forward(request, response);
                    }
                    //如果非文件类型
                    if(item.isFormField()){
                        list.add(item.getString("utf-8"));
                        //参数的类型
//                        System.out.println(item.getString("utf-8"));
                    }else{
                        //判断是否为图片格式
                        if( item.getContentType().equals("image/png") || item.getContentType().equals("image/jpeg")
                                || item.getContentType().equals("image/gif") || item.getContentType().equals("image/bmp") ){
                            //写入文件
                            String rootPath = servletContext.getRealPath("//");
                            String savePath = rootPath+File.separator+"upload";
                            File fileSaveFolder = new File(savePath);
                            if(!fileSaveFolder.exists()){
                                fileSaveFolder.mkdir();
                            }
                            //获取时间
                            Date day = new Date();
                            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                            //完整的存储文件名
                            String saveFileName = savePath+File.separator+df.format(day)+item.getName();
                            //存储文件
                            File uploadedFile = new File(saveFileName);
                            item.write(uploadedFile);
                            try {
                                FileInputStream fis = new FileInputStream(saveFileName);
                                FileOutputStream fos = new FileOutputStream("E:\\JAVA\\java_direction_class\\web\\atb\\javaWebDvd\\image\\"+df.format(day)+item.getName());
                                int n;
                                byte[] bytes = new byte[1024];
                                while ((n = (fis.read(bytes))) != -1) {
                                    fos.write(bytes, 0, n);
                                }
                                fis.close();
                                fos.close();
                            } catch (FileNotFoundException e) {
                                System.out.println("要复制的图片不存在");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            list.add(df.format(day)+item.getName());
                        }else{
                            request.setAttribute("MSG", "图片仅支持png/jpg/gif/bmp格式");
                            request.getRequestDispatcher("./atb/javaWebDvd/jsp/choice/add.jsp").forward(request, response);
                        }
                    }
                }
                JDBCUtilDvd jdbcUtil = new JDBCUtilDvd();
                Dvd dvd1 = new Dvd(list.get(0), Integer.parseInt(list.get(1)),list.get(2));
                int jd = jdbcUtil.addDvd(dvd1);
                if(jd > 0){
                    //添加成功跳转显示界面
                    request.setAttribute("MSG", "添加成功！");
                    request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
                }else{
                    //输出登陆失败
                    request.setAttribute("MSG", "添加失败，该书已存在！");
                    request.getRequestDispatcher("./atb/javaWebDvd/jsp/choice/add.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        int page = Integer.parseInt(request.getParameter("page"));
        request.setAttribute("page", page);
        request.getRequestDispatcher("./atb/javaWebDvd/display.jsp").forward(request, response);
    }
}
