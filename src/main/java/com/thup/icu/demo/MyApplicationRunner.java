package com.thup.icu.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *@Author tlibn
 *@Date 2021/9/11 16:25
 **/
@Component
@Order(InteractiveShellApplicationRunner.PRECEDENCE - 50)
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        DataDb.init();
    }


}
