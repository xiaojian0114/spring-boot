package org.example.task.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.service.MailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
@AllArgsConstructor
@Slf4j
public class DailyReportTask {
    private final MailService mailService;

    @Scheduled(cron = "0 46 15 * * ? ")
    public void sendReport() {
        String report = "这是每日报表的内容";
        mailService.sendMail("2124015394@qq.com","每日数据报表",report);
        log.info("报表已生成并发送邮件已完成：{}", LocalDateTime.now());
    }
}
