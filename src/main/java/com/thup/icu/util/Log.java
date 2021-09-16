package com.thup.icu.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 *@Author tlibn
 *@Date 2021/9/11 15:30
 **/
@Slf4j
public class Log {

    private static String osName = System.getProperty("os.name");

    public static void out(String opVal, String tips, String text){

        if("1".equals(opVal)){
            log(tips, text);
        }else if("2".equals(opVal)){
            out(tips, text);
        }else {
            out(tips, text);
        }
    }

    public static void log(String tips, String text){
        String logStr = getString(tips, text);
        log.info(logStr);
    }

    public static void out2(String tips, String text){
        String logStr = "== " + tips + " == " + text;
        System.out.println(logStr);
    }

    public static void out(String tips, String text){
        String logStr = getString(tips, text);
        System.out.println(logStr);
    }


    private static String getString(String tips, String text) {
        String logStr = "== " + tips + " == " + text;
        if(System.getProperty("os.name").contains("dows")) {
            try {
                logStr = new String(logStr.getBytes(),"gb2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return logStr;
    }

}
