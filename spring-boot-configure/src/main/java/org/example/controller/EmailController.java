package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.Mail;
import org.example.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/mail")
    public void sendEmail(@RequestBody Mail mail) {

        emailService.sendSimpleEmail(mail.getTo(), mail.getSubject(), mail.getBody());

    }
}
