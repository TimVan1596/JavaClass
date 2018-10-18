package com.timvanx.biggerdvd.util;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 七牛云上传封装
 * 1、UploadImage() 上传图片功能
 * @date 2018年10月18日17:30:58
 * @author TimVan
 */
public class QiNiuUpload {
    /**
     * 配置文件名 CONFIG_FILE
     * accessKey = AccessKey的值
     * secretKey = SecretKey的值
     * bucket = 对象空间资源名
     */
    private static String accessKey ;
    private static String secretKey ;
    private static  String bucket ;

    static {
        //配置文件读取(只做一次)
        init();
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

        accessKey = properties.getProperty("AccessKey");
        secretKey = properties.getProperty("SecretKey");
        bucket = properties.getProperty("bucket");
    }

    /**
     * 上传图片功能
     */
    private static void UploadImage(String localImagePath){
        //zong0() 代表华东地区
        Configuration cfg = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(cfg);

        //在七牛云中图片的命名
        String key = "456.png";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response =
                    uploadManager.put(localImagePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson()
                    .fromJson(response.bodyString(), DefaultPutRet.class);

        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }

}
