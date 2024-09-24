package org.example.service;


import cn.hutool.core.io.FileUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;

@Service
@Slf4j
public class ConfigBackupService {

    @Value("${custom.backup.location}")
    private String configFilePath;

    @PostConstruct
    public void backupConfigFile() {
        File configFile = new File(configFilePath);
        if (configFile.exists()) {
            File bakFile = new File("backup/"+ configFile.getName());
            FileUtil.copy(configFile,bakFile,true);
            log.info("配置文件已备份到：{}", bakFile.getPath());
        }
    }
}
