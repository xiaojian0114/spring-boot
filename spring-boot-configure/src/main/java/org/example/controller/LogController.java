package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LogController {
    private final LogService logService;

    @GetMapping("/log")
    public void logMsg(){
        logService.logMessage();
    }
}
