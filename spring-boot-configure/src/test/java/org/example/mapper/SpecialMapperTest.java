package org.example.mapper;

import jakarta.annotation.Resource;
import org.example.entity.Special;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpecialMapperTest {
    @Resource
    private SpecialMapper specialMapper;

    @Test
    void findAll() {
        List<Special> specials = specialMapper.findAll();
        specials.forEach(System.out::println);
    }

}