package org.example.file.untils;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MinIoTemplateTest {

    @Resource
    private MinIoTemplate minIoTemplate;

    @Test
    void bucketExists() throws Exception {
        boolean flag = minIoTemplate.bucketExists("avatar");
        log.info(String.valueOf(flag));
    }

    @Test
    void makeBucket() throws Exception {
        minIoTemplate.makeBucket("mqxu");
    }

    @Test
    void putObject() throws Exception {
        File file = new File("Users/moqi/Pictures/me.png");

        String url = minIoTemplate.putObject("mqxu" , "test/" + UUID.randomUUID() + ".png" , new FileInputStream(file));
        log.info(url);
    }

    @Test
    void removeObject() throws Exception {

        minIoTemplate.removeObject("mqxu" , "img/test.jpg");
    }
}