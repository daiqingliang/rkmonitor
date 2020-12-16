package com.daiql.rkmonitor.sendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author daiql
 * @description 测试邮件发送
 * @date 2020/12/16 11:48
 */

//@Component
public class SendMailTest implements ApplicationRunner {

    @Autowired
    private IMailService mailService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("********************测试邮件发送********************");
        mailService.sendSimpleMail("*****@qq.com", "主题：测试普通邮件发送", "测试普通邮件发送");
    }

}
