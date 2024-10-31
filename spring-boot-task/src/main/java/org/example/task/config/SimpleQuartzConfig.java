package org.example.task.config;

import org.example.task.jobs.SimpleQuartzTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SimpleQuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(SimpleQuartzTask.class)
                .withIdentity("SimpleQuartzTask")
                .storeDurably()
                .build();

    }

    @Bean
    public Trigger trigger() {
        // 指定每五秒执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("SimpleQuartzTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
