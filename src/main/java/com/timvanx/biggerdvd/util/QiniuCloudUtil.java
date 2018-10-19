package com.timvanx.biggerdvd.util;

/**
 * @author TimVan
 * @date 2018/10/18 22:25
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
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

    /**
     * 数据流上传
     * InputStream对象的上传，适用于所有的InputStream子类
     * @param fileInputStream InputStream对象
     */
    public static void uploadStreamToCloud(FileInputStream fileInputStream){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());

        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager
                        .put(fileInputStream
                        ,key,upToken,null,null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson()
                        .fromJson(response.bodyString()
                                , DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
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
