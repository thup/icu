package com.thup.icu.demo;

import com.alibaba.fastjson.JSONObject;
import com.thup.icu.dto.BaseDto;
import com.thup.icu.util.FileUtil;
import com.thup.icu.util.Log;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 *@Author tlibn
 *@Date 2021/9/11 12:54
 **/
public class DataDb {

    //jar所在目录
    public static String jarPath = "";
    //控制台输出配置 1 详细输出 2 简单输出
    public static String output = "2";

    /**
     * 工具配置参数
     */
    private static JSONObject configJson = new JSONObject();

    static {
        configJson.put(Constants.output
                , new BaseDto(Constants.output
                        ,"1",
                        "-o --output 控制台输出配置 1 详细输出 2 简单输出"));
    }

    /**
     * 命令参数
     */
    private static JSONObject commandJson = new JSONObject();

    public static JSONObject getConfig(){
        return configJson;
    }

    public static BaseDto getConfig(String key){
        return configJson.getObject(key, BaseDto.class);
    }
    public static String getConfigValue(String key){

        BaseDto baseDto = getConfig(key);
        if(Objects.isNull(baseDto)){
            return null;
        }
        return baseDto.getValue();
    }
    public static void setConfig(JSONObject configJson1){
        configJson = configJson1;
    }

    public static BaseDto setConfig(String key, String value){
        return setConfig(key, value,null);
    }
    public static BaseDto setConfig(String key, String value, String desc){
        return set(key, value, desc, configJson, 1);
    }

    public static void setCommand(JSONObject commandJson1){
        commandJson = commandJson1;
    }

    public static BaseDto setCommand(String key, String value){
        return setCommand(key, value, null);
    }
    public static BaseDto setCommand(String key, String value, String desc){
        return set(key, value, desc, commandJson, 1);
    }

    public static BaseDto getCommand(String key){
        return commandJson.getObject(key, BaseDto.class);
    }
    public static String getCommandValue(String key){

        BaseDto baseDto = getCommand(key);
        if(Objects.isNull(baseDto)){
            return null;
        }
        return baseDto.getValue();
    }

    public static JSONObject getCommand(){
        return commandJson;
    }

    /**
     *
     * @param key
     * @param json
     * @param tpye 1 配置 2 命令
     * @return
     */
    private static BaseDto set(String key
            , String value
            , String desc, JSONObject json, int tpye) {
        BaseDto baseDto = getConfig(key);

        if (tpye==1&&baseDto == null) {
            System.out.println("未查到对应参数-" + key);
        }

        if(StringUtils.isEmpty(desc)){
            desc = "未设置";
            if(Objects.nonNull(baseDto)){
                desc = baseDto.getDesc();
            }
        }

        BaseDto newBaseDto = new BaseDto(key, value, desc);
        json.put(key, newBaseDto);
        return newBaseDto;
    }


    public static void init() {
        String jarPath = FileUtil.getJarPath();
        DataDb.jarPath = jarPath;

        Log.log("util path",jarPath);

        String confStr = FileUtil
                .getText(jarPath + Constants.confFile);

        Log.out("config info",confStr);

        DataDb.setConfig(JSONObject.parseObject(confStr));
        Log.out("dataDb 配置 info",DataDb.getConfig().toJSONString());


        String commandStr = FileUtil
                .getText(jarPath + Constants.commandFile);
        Log.out("comman info",commandStr);

        DataDb.setCommand(JSONObject.parseObject(commandStr));
        Log.out("dataDb 命令 info",DataDb.getCommand().toJSONString());

        DataDb.output = DataDb.getConfigValue(Constants.output);

    }



    public static void main(String[] args) {

    }

}
