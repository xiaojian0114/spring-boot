package org.example.task.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.entity.EmailLog;
import org.example.task.mapper.EmailLogMapper;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final EmailLogMapper emailLogMapper;

    public void sendMail(String to, String subject, String content) {

        //1.发邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2124015394@qq.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);

        EmailLog emailLog = new EmailLog();
        emailLog.setRecipient(to);
        emailLog.setSubject(subject);
        emailLog.setContent(content);
        emailLog.setSentAt(LocalDateTime.now());
        emailLogMapper.insert(emailLog);

        log.info("邮件发送至： {}",to);
    }

}
