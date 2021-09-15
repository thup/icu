package com.thup.icu.demo;


import java.io.File;

/**
 * 公共的常量值
 * @Author tlibn
 * @Date 2019/8/2 11:32
 **/

public class Constants {

    /**
     * 配置-输出 output
     */
    public final static String output = "output";

    public final static String confFile = File.separator+"conf.json";
    public final static String commandFile = File.separator+"command.json";

    /**
     * 特殊字符
     */
    public static final String[] SPECIAL_CHAR = new String[]{"!","@","#","$","%","^","&","*","(",")","-","=","~","·"};


}
