package com.daiql.rkmonitor;

import com.daiql.rkmonitor.html.HtmlHandler;
import com.daiql.rkmonitor.sendmail.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;

/**
 * @author daiql
 * @description 定时任务
 * @date 2020/12/16 21:36
 */
@Configuration
@EnableScheduling
public class ScheduleTask {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Value("${to.mails}")
    private String mails;

    @Autowired
    private IMailService mailService;

    @Scheduled(cron = "${schedule}") //每隔10分钟
    private void sendMailTask() {
        String url = "https://www.ruankao.org.cn/";
        logger.info("开始检查2020年软考成绩是否可以查询...");
        List<String> list = HtmlHandler.getUnorderedList(url);
        for (String str : list) {
            if (str.contains("2020")) {
                logger.info("2020年软考成绩已出，开始发送邮件...");
                sendMail();
                break;
            }
        }
    }

    /**
     * 发送邮件
     */
    private void sendMail() {
        Arrays.stream(mails.split(",")).forEach(mail -> {
            mail = mail.trim();
            logger.info(" Send mail to " + mail);
            mailService.sendSimpleMail(mail, "2020年下半年成绩已出！", "2020年下半年成绩已出！请尽快查询，大家可以抱头疼哭了！");
        });
    }
}
