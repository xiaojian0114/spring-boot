package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.result.Result;
import org.example.service.SmsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
@AllArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @PostMapping("send")
    public Result<Object> sendSms(@RequestParam("phone") String phone) {
        smsService.sendSms(phone);
        return Result.ok();
    }
}
