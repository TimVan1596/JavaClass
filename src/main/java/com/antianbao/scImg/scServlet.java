package com.antianbao.scImg;

import org.apache.tomcat.util.http.fileupload.FileItem;
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
import java.util.*;

//import com.bjpowernode.drp.basedata.manager.ItemManager;
//import com.bjpowernode.drp.basedata.manager.ItemManagerImpl;
//import com.bjpowernode.drp.util.ApplicationException;

@WebServlet(name = "scServlet",urlPatterns = {"/sc.do"})
public class scServlet extends HttpServlet {
    private File uploadPath;
    private File tempPath;

    @Override
    public void init() throws ServletException {
        //在系统启动的时候，就开始初始化，在初始化时，检查上传图片的文件夹和存放临时文件的文件夹是否存在，如果不存在，就创建
        //获取根目录对应的真实物理路径
        uploadPath = new File(getServletContext().getRealPath("upload"));
        System.out.println("uploadPath=====" + uploadPath);
        //如果目录不存在
        if (!uploadPath.exists()) {
            //创建目录
            uploadPath.mkdir();
        }
        //临时目录
        //File tempFile = new File(item.getName())构造临时对象
        tempPath = new File(getServletContext().getRealPath("temp"));
        if (!tempPath.exists()) {
            tempPath.mkdir();
        }
        //如果不显示调用父类方法，就不会有itemManager实例，因此会造成空指针
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post解决中文乱码
        request.setCharacterEncoding("utf-8");
        //实例化一个硬盘文件工厂，用来配置文件组建ServletFileUpload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        try{
            List fileItems = upload.parseRequest((RequestContext) request);
            String itemNo = "";
            for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
                //获得序列中的下一个元素
                FileItem item = (FileItem) iter.next();
                //判断是文件还是文本信息
                //是普通的表单输入域
                if(item.isFormField()) {
                    if ("itemNo".equals(item.getFieldName())) {
                        itemNo = item.getString();
                    }
                }
                //是否为input="type"输入域
                if (!item.isFormField()) {
                    //上传文件的名称和完整路径
                    String fileName = item.getName();

                    long size = item.getSize();
                    //判断是否选择了文件
                    if ((fileName == null || fileName.equals("")) && size == 0) {
                        continue;
                    }
                    //截取字符串 如：C:\WINDOWS\Debug\PASSWD.LOG
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
                    // 保存文件在服务器的物理磁盘中：第一个参数是：完整路径（不包括文件名）第二个参数是：文件名称
                    //item.write(file);
                    //修改文件名和物料名一致，且强行修改了文件扩展名为gif
                    //item.write(new File(uploadPath, itemNo + ".gif"));
                    //将文件保存到目录下，不修改文件名
                    item.write(new File(uploadPath, fileName));
                    //将图片文件名写入打数据库
                    //itemManager.uploadItemImage(itemNo, fileName);
                }
            }
            response.sendRedirect(request.getContextPath() + "/servlet/item/SearchItemServlet");
//            String name = request.getParameter("name");
//            //乱码问题
//            upload.setHeaderEncoding("UTF-8");
//            FileItemIterator iter = upload.getItemIterator(request);
//            InputStream inputStream = iter.next().openStream();
//            JDBCUtil jdbcUtil = new JDBCUtil();
//            int jd = jdbcUtil.addDvd(name,inputStream);
//            System.out.println(jd);
        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get解决中文乱码
        //name = new String(name.getBytes("ISO-8859-1"),"utf-8");
        doPost(request,response);
    }
}
