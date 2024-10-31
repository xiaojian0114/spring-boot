package org.example.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest
class TestBeanServiceTest {

    @Resource
    private ConfigurableApplicationContext ioc;

    @Test
    void test() {
        TestBeanService testBeanService = (TestBeanService) ioc.getBean("testBeanService");
        System.out.println(testBeanService.getName());
    }

}