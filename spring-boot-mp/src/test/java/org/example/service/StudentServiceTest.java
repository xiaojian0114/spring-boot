package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudentServiceTest {
    @Resource
    private StudentService studentService;

    @Test
    void getStudentWithCourses() {
        Student student = studentService.getStudentWithCourses(1L);
        log.info("{},{}", student.getId(), student.getName());
        student.getCourses().forEach(course -> log.info("{},{}", course.getId(), course.getName()));
    }
}