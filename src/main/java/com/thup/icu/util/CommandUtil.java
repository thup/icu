package com.thup.icu.util;

import com.alibaba.fastjson.JSONObject;
import com.thup.icu.demo.ServiceException;
import com.thup.icu.dto.RunCaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *@Author tlibn
 *@Date 2021/8/24 17:20
 **/
@Slf4j
@Component
public class CommandUtil {

    public static void main(String[] args) {
        CommandUtil commandUtil = new CommandUtil();
        String command = "java -version";
        commandUtil.run(command);
    }
    public void run(String command){
        Runtime run =Runtime.getRuntime();
        try {
            //Process p = run.exec("ping 127.0.0.1");
            Process p = run.exec(command);
            //Process p = run.exec("jmeter -n -t G:/ceba/jmeter_install_dir/demo/10246_13/13.jmx  -l G:/ceba/jmeter_install_dir/demo/10246_13/log/13 -e -o G:/ceba/jmeter_install_dir/demo/10246_13/report/13");

            InputStream ins= p.getInputStream();
            InputStream ers= p.getErrorStream();
            new Thread(new CommandUtil.inputStreamThread(ins)).start();
            new Thread(new CommandUtil.inputStreamThread(ers)).start();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    class inputStreamThread implements Runnable{
        private InputStream ins = null;
        private BufferedReader bfr = null;
        public inputStreamThread(InputStream ins){
            this.ins = ins;
            this.bfr = new BufferedReader(new InputStreamReader(ins));
        }
        @Override
        public void run() {
            String line = null;
            byte[] b = new byte[100];
            int num = 0;
            try {
                while((num=ins.read(b))!=-1){
                    System.out.println(new String(b,"gb2312"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

