package com.smallfangyu.data;

import org.apache.log4j.Logger;

public class LogUtil {
private static Logger logger;
private static LogUtil util;

    static{
        logger=Logger.getLogger(LogUtil.class);
    }

    public Logger getLogger(){

        return logger;
    }

    public static LogUtil getInstance(){

        if(util==null){
            util=new LogUtil();
        }
        return util;
    }
}
