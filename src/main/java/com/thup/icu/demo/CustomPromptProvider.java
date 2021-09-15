package com.thup.icu.demo;

import com.alibaba.fastjson.JSONObject;
import com.thup.icu.util.FileUtils;
import com.thup.icu.util.Log;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *@Author tlibn
 *@Date 2021/9/10 23:23
 **/
@Component
public class CustomPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {

        // 获取主机名称
        String hostName = getHostName();

        // 设置命令提示符文字
        String promot = "ceshiren@" + hostName + "> ";

        // 设置命令提示符字体样式
        AttributedStyle promotStyle = AttributedStyle.BOLD.foreground(AttributedStyle.GREEN);
        // 返回命令提示符
        return new AttributedString(promot, promotStyle);
    }

    /**
     * @Description: 获取主机名称
     * @return: String 主机名称
     * @author: zongf
     * @time: 2019-01-26 08:58:45
     */
    private String getHostName(){
        String hostName = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }


}
