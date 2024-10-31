package org.example.task.timer;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class CountdownTimer {
    public static void main(String[] args) {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            int countdown = 10;

            @Override
            public void run() {

                if ( countdown > 0) {
                    log.info("倒计时: {} 秒", countdown);
                    countdown--;
                }else {
                    log.info("倒计时结束");
                    timer.cancel();
                }



            }
        };

        timer.schedule(task,0,1000);
    }
}
