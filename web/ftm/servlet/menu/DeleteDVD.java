package web.ftm.servlet.menu;

import com.alibaba.fastjson.JSON;
import com.timvanx.biggerdvd.dvd.DVD;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 编辑的DVD的信息
 * @author TimVan
 */
@WebServlet(name = "DeleteDVD",
        urlPatterns = {"/ftm/html/menu/DeleteDVD.do"}, loadOnStartup = 1)
public class DeleteDVD extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/text; charset=utf-8");
        PrintWriter out = response.getWriter();

        String dvdID =  request.getParameter("id");
        int id = Integer.parseInt(dvdID);
        String dvdName =  request.getParameter("name");
        System.out.println("dvdName ="+dvdName);

        Map<String, Object> ret = new HashMap<>(1);
        //删除DVD操作
        //-1 = 找到，但未归还 , 0 = 未找到  , 1 = 删除成功
        int retStatus = DVD.deleteDVDForWeb(id);
        if (retStatus == 1) {
            ret.put("error", 0);
        }
        else if(retStatus == -1){
            ret.put("error", 1);
            ret.put("errorInfo", "此DVD还未归还！");
        }
        else if(retStatus == 0){
            ret.put("error", 2);
            ret.put("errorInfo", "未找到DVD！");
        }
        else {
            ret.put("error", 3);
            ret.put("errorInfo", "编辑DVD信息出错");
        }

        //使用 Alibaba fastJson 序列化 ret
        String retJson = JSON.toJSONString(ret);
        out.write(retJson);
    }

}