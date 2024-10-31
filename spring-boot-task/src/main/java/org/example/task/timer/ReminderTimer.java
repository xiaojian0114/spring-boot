package org.example.task.timer;

import java.util.Timer;
import java.util.TimerTask;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReminderTimer {

    public static void main(String[] args) {
        // 计时器
        Timer timer = new Timer();
        // 任务
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                 // 要做的事
                log.info("请休息一下，喝口水吧");
            }
        };
        //每隔五秒
        timer.schedule(task , 0 , 5000);

    }

}
