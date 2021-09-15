package com.thup.icu.demo;

import com.alibaba.fastjson.JSONObject;
import com.thup.icu.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

@Slf4j
@ShellComponent()
public class ConfigCommands {

    @ShellMethod(value = "工具配置", key = "conf")
    public String config(
            @ShellOption(value = {"-k", "--key"}) @NotBlank(message = "key不能为空") String key
            , @ShellOption(value = {"-v", "--value"}) @NotBlank(message = "value不能为空") String value
            , @ShellOption(value = {"-d", "--desc"}) String desc) {

        DataDb.setConfig(key, value, desc);

        String configStr = DataDb.getConfig().toJSONString();

        FileUtil.saveTextFile(configStr,DataDb.jarPath+Constants.confFile);

        return "配置成功-"+configStr;
    }

    @ShellMethod(value = "添加或修改命令", key = "add")
    public String add(
            @ShellOption(value = {"-k", "--key"}) @NotBlank(message = "key不能为空") String key
            , @ShellOption(value = {"-v", "--value"}) @NotBlank(message = "value不能为空") String value
            , @ShellOption(value = {"-d", "--desc"}) String desc) {

        desc = FileUtil.getStr(desc);
        DataDb.setCommand(key, value, desc);
        String configStr = DataDb.getCommand().toJSONString();
        FileUtil.saveTextFile(configStr,DataDb.jarPath+Constants.commandFile);

        return "添加成功-" + configStr;
    }

    @ShellMethod(value = "工具初始化", key = "init")
    public void init() {
        DataDb.init();
    }

    @ShellMethod(value = "查看信息", key = "show")
    public String show(
            @ShellOption(value = {"-t", "--type"}) String type
            , @ShellOption(value = {"-k", "--key"}) String key
    ) {
        if("f".equalsIgnoreCase(type)){
            String configStr = "none";
            if(StringUtils.isEmpty(key) || "all".equalsIgnoreCase(key)){
                configStr = DataDb.getConfig().toJSONString();
            }else {
                configStr = JSONObject.toJSONString(DataDb.getConfig(key));
            }
            return "配置信息" + configStr;
        }else if("d".equalsIgnoreCase(type)){
            String configStr = "none";
            if(StringUtils.isEmpty(key) || "all".equalsIgnoreCase(key)){
                configStr = DataDb.getCommand().toJSONString();
            }else {
                configStr = JSONObject.toJSONString(DataDb.getCommand(key));
            }
            return "命令信息" + configStr;
        }

        return "查看工具配置的命令信息成功";
    }

}
