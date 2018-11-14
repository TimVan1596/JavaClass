//package com.antianbao.javaWebDvd.jsp.choice;
//
//import com.alibaba.fastjson.JSON;
//import com.timvanx.biggerdvd.util.QiniuCloudUtil;
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.RequestContext;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
///**
// * 编辑的DVD的信息
// * @author TimVan
// */
//@WebServlet(name = "imageServlet",urlPatterns = {"/imageServlet.do"})
//public class imageServlet extends HttpServlet {
//
//    /**
//     * UPLOAD_DIRECTORY = 上传文件存储目录
//     * */
//    private static final String UPLOAD_DIRECTORY = "upload";
//
//    private static final int MEMORY_THRESHOLD
//            = 1024 * 1024 * 3;
//    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40;
//    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;
//
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        doPost(request, response);
//    }
//
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("application/text; charset=utf-8");
//        PrintWriter out = response.getWriter();
//        //返回值jSON,键值对
//        Map<String, Object> ret = new HashMap<>(1);
//
//        // 检测是否为多媒体上传
//        if (!org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload.isMultipartContent(request)) {
//            // 如果不是则停止
//            ret.put("error", 1);
//            ret.put("errorInfo", " 表单必须包含 enctype=multipart/form-data");
//        }else{
//            // 配置上传参数
//            org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory factory = new DiskFileItemFactory();
//            // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
//            factory.setSizeThreshold(MEMORY_THRESHOLD);
//            // 设置临时存储目录
//            factory.setRepository(
//                    new File(System
//                            .getProperty("java.io.tmpdir")));
//
//            org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // 设置最大文件上传值
//            upload.setFileSizeMax(MAX_FILE_SIZE);
//            // 设置最大请求值 (包含文件和表单数据)
//            upload.setSizeMax(MAX_REQUEST_SIZE);
//            // 中文处理
//            upload.setHeaderEncoding("UTF-8");
//
//            // 构造临时路径来存储上传的文件
//            // 这个路径相对当前应用的目录
//            String uploadPath = request.getServletContext()
//                    .getRealPath("./") + File.separator
//                    + UPLOAD_DIRECTORY;
//
//            // 如果目录不存在则创建
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            try {
//
//                RequestContext requestContext
//                        = new ServletRequestContext(request);
//                // 解析请求的内容提取文件数据
//                @SuppressWarnings("unchecked")
//                List<org.apache.tomcat.util.http.fileupload.FileItem> formItems = upload.parseRequest(requestContext);
//
//                if (formItems != null && formItems.size() > 0) {
//                    // 迭代表单数据
//                    for (FileItem item : formItems) {
//                        // 处理不在表单中的字段
//                        if (!item.isFormField()) {
//
//
//                            String fileName = System.currentTimeMillis()+
//                                    "."+(item.getName().split("\\."))[1];
//                            String filePath = uploadPath + File.separator
//                                    + fileName;
//                            File storeFile = new File(filePath);
//                            // 在控制台输出文件的上传路径
//                            System.out.println(filePath);
//                            // 保存文件到硬盘
//                            item.write(storeFile);
//
//                            String retURL = QiniuCloudUtil.uploadStreamToCloud(
//                                    new FileInputStream(storeFile)
//                            );
//
//                            ret.put("error", 0);
//                            ret.put("data", retURL);
//
//                        }
//                    }
//                }
//            } catch (Exception ex) {
//                ret.put("error", 2);
//                ret.put("errorInfo", ex.getMessage());
//            }
//        }
//
//        //使用 Alibaba fastJson 序列化 ret
//        String retJson = JSON.toJSONString(ret);
//        out.write(retJson);
//    }
//
//}