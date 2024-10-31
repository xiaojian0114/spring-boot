package org.example.task.config;

import org.example.task.jobs.ExportJob;
import org.example.task.jobs.SimpleQuartzTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExportQuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(ExportJob.class)
                .withIdentity("ExportQuartzTask")
                .storeDurably()
                .build();

    }

    @Bean
    public Trigger trigger() {
        // 指定每五秒执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("ExportQuartzTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
