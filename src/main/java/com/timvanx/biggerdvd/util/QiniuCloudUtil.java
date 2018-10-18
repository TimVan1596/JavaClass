package com.timvanx.biggerdvd.util;

/**
 * @author TimVan
 * @date 2018/10/18 22:25
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 七牛云上传文件工具类
 */
public class QiniuCloudUtil {


    /**
     * 配置文件名 CONFIG_FILE
     * accessKey = AccessKey的值
     * secretKey = SecretKey的值
     * bucket = 对象空间资源名
     * DOMAIN = 上传路径
     * auth = 密钥
     */
    private static String accessKey ;
    private static String secretKey ;
    private static  String bucket ;
    private static  String DOMAIN ;
    private static  Auth auth;

    static {
        //配置文件读取(只做一次)
        init();
    }


    public static String getUpToken() {
        return auth.uploadToken(bucket, null, 3600, new StringMap().put("insertOnly", 1));
    }


    //base64方式上传
    public static String put64image(byte[] base64, String key) throws Exception{
        String file64 = Base64.encodeToString(base64, 0);
        Integer len = base64.length;

        //华北空间使用 upload-z1.qiniu.com，
        // 华南空间使用 upload-z2.qiniu.com，
        // 北美空间使用 upload-na0.qiniu.com
        String url = "http://upload.qiniu.com/putb64/" + len + "/key/"+ UrlSafeBase64.encodeToString(key);

        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        //System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);

        return DOMAIN + key ;
    }

    /**
     * 配置文件读取(只做一次)
     * <ul>
     * <li>初始化七牛云SDK连接配置（.properties）</li>
     * <li>获取Access Key和Secret Key</li>
     * </ul>
     */
    private static void init(){

        //使用Properties读取配置文件
        // CONFIG_FILE 默认为 "ftmdb.properties"
        final String CONFIG_FILE = "ftmdb.properties";


        Properties properties = new Properties();
        InputStream in = JDBCUtil.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE);

        try {
            properties.load(in);
        } catch (IOException e) {
            Constants.reportError("数据库配置文件");
            e.printStackTrace();
        }
        DOMAIN = properties.getProperty("DOMAIN");
        accessKey = properties.getProperty("AccessKey");
        secretKey = properties.getProperty("SecretKey");
        bucket = properties.getProperty("bucket");
        auth = Auth.create(accessKey, secretKey);
    }
}
