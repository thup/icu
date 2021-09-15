package com.thup.icu.demo;

import com.thup.icu.util.CmdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellOption;

@Slf4j
@ShellComponent()
public class MyCommands {

    @ShellMethod("运行命令 \n \t\t 测试换行")
    public String sh(
            @ShellOption(value = {"-c", "--command"})String key) {

        String command = DataDb.getCommandValue(key);
        //commandUtil.run(command);
        Process process = CmdUtil.exec(command);
        if(CmdUtil.isSuccess(process)){
            CmdUtil.printProcess(process);
            return "运行成功";
        }

        return "运行失败";
    }

}
