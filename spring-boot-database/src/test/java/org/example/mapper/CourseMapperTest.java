package org.example.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Course;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class CourseMapperTest {
    @Resource
    private CourseMapper courseMapper;

    @Test
    void selectAll() {
        Course course  = courseMapper.getCourseWithStudents(20001);
        log.info("课程名称：{}" , course.getCourseName());
        course.getStudents().forEach(student -> log.info("学生姓名：{} ， 家乡：{}",student.getStudentName() , student.getHometown()));
    }
}