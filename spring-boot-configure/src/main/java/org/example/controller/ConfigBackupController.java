package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.ConfigBackupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConfigBackupController {
    private final ConfigBackupService configBackupService;

    @GetMapping("/backup")
    public void backupConfig() {
        configBackupService.backupConfigFile();
    }
}
