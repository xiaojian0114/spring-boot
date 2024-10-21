package org.example;

import jakarta.annotation.Resource;
import org.example.entity.Address;
import org.example.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String , Student> redisTemplate;

    @Test
    void test1() {
        stringRedisTemplate.opsForValue().set("hello", "world",60, TimeUnit.SECONDS);
    }

    @Test
    void test2() {
        Address address = Address.builder().province("江苏").city("南京").build();
        Student student = Student.builder().id("123").name("张三").address(address).build();

        redisTemplate.opsForValue().set("student", student,120, TimeUnit.SECONDS);
    }
}
