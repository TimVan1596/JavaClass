package com.antianbao.text.log4j;

import org.apache.log4j.Logger;

public class log4jTest {

    Logger logger = Logger.getLogger(log4jTest.class);

    public void printLogger() {

        logger.debug("测试");

    }

    public static void main(String[] args) {
        log4jTest log4jTest = new log4jTest();
        log4jTest.printLogger();
    }

}
