package com.antianbao.javaWebDvd;

import org.apache.log4j.Logger;

public class log4j {
    private static Logger logger;
    private static log4j util;

    static{
        logger=Logger.getLogger(log4j.class);
    }

    public Logger getLogger(){

        return logger;
    }

    public static log4j getInstance(){

        if(util==null){
            util=new log4j();
        }
        return util;
    }
}
