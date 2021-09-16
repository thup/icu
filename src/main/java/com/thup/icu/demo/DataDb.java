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

    /**
     * 命令参数
     */
    private static JSONObject commandJson = new JSONObject();


    static {
        getDefaultConf();
        getDefaultCommand();
    }

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
        return set(key, value, desc, commandJson, 2);
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
            System.out.println("not found -" + key);
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

        String confStr = "";
        String confPath = jarPath + Constants.confFile;
        Log.log("conf path",confPath);
        if(FileUtil.existsFile(confPath)){
            confStr = FileUtil.getText(confPath);
            DataDb.setConfig(JSONObject.parseObject(confStr));
        }else {
            confStr = DataDb.getConfig().toJSONString();
            FileUtil.saveTextFile(confStr ,confPath);
        }

       /* Log.out2("config info",confStr);

        Log.out2("dataDb 配置 info",DataDb.getConfig().toJSONString());*/
        DataDb.output = DataDb.getConfigValue(Constants.output);

        String commandStr = "";
        String commandPath = jarPath + Constants.commandFile;
        Log.log("command path",commandPath);
        if(FileUtil.existsFile(commandPath)){
            commandStr = FileUtil.getText(commandPath);
            DataDb.setCommand(JSONObject.parseObject(commandStr));
        }else {
            commandStr = DataDb.getCommand().toJSONString();
            FileUtil.saveTextFile(commandStr ,commandPath);
        }

        /*Log.out2("command info",commandStr);

        Log.out2("dataDb 命令 info",DataDb.getCommand().toJSONString());*/

    }



    public static void main(String[] args) {

    }

        public static void getDefaultConf() {

        configJson.put(Constants.output
                , new BaseDto(Constants.output
                        ,"1",
                        "-o --output 控制台输出配置 1 详细输出 2 简单输出"));
    }
    public static void getDefaultCommand() {

        commandJson.put("n1"
                , new BaseDto("n1"
                        ,"node -v",
                        "get node version"));
        commandJson.put("v1"
                , new BaseDto("v1"
                        ,"vue -V",
                        "get vue version"));
        commandJson.put("g1"
                , new BaseDto("g1"
                        ,"git --version",
                        "get git version"));
        commandJson.put("pi"
                , new BaseDto("pi"
                        ,"pip -V",
                        "get pip version"));
    }

}
