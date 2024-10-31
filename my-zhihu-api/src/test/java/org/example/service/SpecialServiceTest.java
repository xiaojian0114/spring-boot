package org.example.service;

import jakarta.annotation.Resource;
import org.example.enity.Special;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpecialServiceTest {

    @Resource
    private SpecialService specialService;

    @Test
    void getAll() {
        List<Special> all = specialService.getAll();
        all.forEach(System.out::println);
    }

    @Test
    void testGetAll() {
    }

    @Test
    void getByPage() {
    }
}