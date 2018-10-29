package com.timvanx.biggerdvd.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.baidu.aip.ocr.AipOcr;
import com.qiniu.util.Auth;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


/**
 * 调用百度OCR进行图片识别，单例封装
 */
public class BaiduOCR {


    /**
     * 设置APPID/AK/SK
     */
    private static  String APP_ID;
    private static  String API_KEY ;
    private static  String SECRET_KEY;


    /**
     * 设置AipOcr
     */
    private AipOcr client;

    private static BaiduOCR instance = new BaiduOCR();
    private static int NO_ERROR = 0;

    private BaiduOCR() {
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    }

    public static BaiduOCR getInstance() {
        return instance;
    }

    /**
     * 图片识别成文字(学生卡)
     */
    public HashMap<String, String> imageToStuCard(String path) {
        //初始化APP_ID、API_KEY等配置信息
        init("stuCard");

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        //返回值
        HashMap<String, String> retval = new HashMap<String, String>();
        //检测图像朝向
        options.put("detect_direction", "true");


        String templateSign = "6777ef4d7a16ba73d167f66a0bc9294d";

        // 参数为本地图片路径
        String image = path;
        JSONObject res = client.custom(image, templateSign, options);


        int error_code = res.getInt("error_code");
        if (error_code == NO_ERROR) {
            JSONObject data = res.getJSONObject("data");
            JSONArray ret = data.getJSONArray("ret");
            //取学生卡姓名、学号、班级
            JSONObject nameObj = (JSONObject) ret.get(0);
            String name = nameObj.getString("word");
            JSONObject idObj = (JSONObject) ret.get(1);
            String id = idObj.getString("word");
            JSONObject deptObj = (JSONObject) ret.get(2);
            String dept = deptObj.getString("word");

            retval.put("name", name);
            retval.put("id", id);
            retval.put("dept", dept);
            retval.put("error", "0");


        } else {

            retval.put("error", "1");
            retval.put("error_msg", res.getString("error_msg"));
        }

        return retval;
    }

    /**
     * 身份证识别成文字
     */
    public HashMap<String, String> idImageToIDCard(String path){
        //初始化APP_ID、API_KEY等配置信息
        init("idCard");

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        //返回值
        HashMap<String, String> retval = new HashMap<String, String>();
        //检测图像朝向
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");

        String idCardSide = "front";

        // 参数为本地图片路径
        JSONObject res  = client.custom(path, idCardSide, options);

        String image_status = res.getString("image_status");
        if ("normal".equals(image_status)) {
            JSONObject data = res.getJSONObject("data");
            JSONArray ret = data.getJSONArray("words_result");
            //获取住址、号码、出生日期、姓名、性别、民族
            JSONObject addressObj = (JSONObject) ret.get(0);
            String address = addressObj.getString("word");

            JSONObject idObj = (JSONObject) ret.get(1);
            String id = idObj.getString("word");

            JSONObject dateObj = (JSONObject) ret.get(2);
            String date = dateObj.getString("word");

            JSONObject nameObj = (JSONObject) ret.get(3);
            String name = nameObj.getString("word");

            JSONObject sexObj = (JSONObject) ret.get(4);
            String sex = sexObj.getString("word");

            JSONObject rationObj = (JSONObject) ret.get(5);
            String ration = rationObj.getString("word");

            retval.put("address", address);
            retval.put("id", id);
            retval.put("date", date);
            retval.put("name", name);
            retval.put("sex", sex);
            retval.put("ration", ration);
            retval.put("error", "0");

        } else {
            retval.put("error", "1");
            retval.put("error_msg", image_status);
        }
        System.out.println(res.getString("ret"));

        return retval;
    }


    /**
     * 配置文件读取(只做一次)
     */
    private static void init(String option){

        //使用Properties读取配置文件
        // CONFIG_FILE 默认为 "ftmdb.properties"
        final String CONFIG_FILE = "ftmdb.properties";


        Properties properties = new Properties();
        InputStream in = BaiduOCR.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE);

        try {
            properties.load(in);
        } catch (IOException e) {
            Constants.reportError("BaiduOCR配置文件");
            e.printStackTrace();
        }

        if ("stuCard".equals(option)){
            APP_ID = properties.getProperty("AIP_STUCARD_APP_ID");
            API_KEY = properties.getProperty("AIP_STUCARD_API_KEY");
            SECRET_KEY = properties.getProperty("AIP_STUCARD_SECRET_KEY");
        }
        else if ("idCard".equals(option)){
            APP_ID = properties.getProperty("AIP_IDCARD_APP_ID");
            API_KEY = properties.getProperty("AIP_IDCARD_API_KEY");
            SECRET_KEY = properties.getProperty("AIP_IDCARD_SECRET_KEY");
        }



    }

    public static void main(String[] args) {

        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
        //SingleObject object = new SingleObject();

        //获取唯一可用的对象
        BaiduOCR object = BaiduOCR.getInstance();

        //显示消息
        object.idImageToIDCard
                ("C:\\Users\\r\\IdeaProjects\\" +
                        "java_direction_class\\src\\main\\timg.jpg");
    }

}

