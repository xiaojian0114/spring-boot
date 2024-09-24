package org.example.service;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Value("${custom.log.level}")
    private String logLevel;

    private final Log log = LogFactory.get();

    public void logMessage() {
        log.info("当前日志级别位："+logLevel);
        if ("DEBUG".equalsIgnoreCase(logLevel)) {
            log.debug("这是调试信息");
        }else if ("INFO".equalsIgnoreCase(logLevel)) {
            log.info("这是普通信息");
        }
    }
}
