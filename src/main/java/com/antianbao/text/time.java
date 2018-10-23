package com.antianbao.text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class time {
    public static void main(String[] args) {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(df.format(day));
    }
}
