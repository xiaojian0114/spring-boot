package org.example.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Clazz;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j

class ClazzMapperTest {

    @Resource
    private ClazzMapper clazzMapper;

    @Test
    void getClazzById() {
        Clazz clazz = clazzMapper.getClazzById(1);
        log.info("{},{}" , clazz.getClazzId() , clazz.getClazzName());
        List<Student> students = clazz.getStudents();
        students.forEach(student -> log.info("{} , {} , {}" , student.getStudentId(),student.getStudentName() , student.getHometown()));
    }

    @Test
    void getClazz() {
        Clazz clazz = clazzMapper.getClazz(1);

        if (clazz != null) {
            Teacher teacher = clazz.getTeacher();
            if (teacher != null) {
                log.info("任课老师：{}", teacher.getTeacherName());
            } else {
                log.info("该班级没有任课老师");
            }

            // 处理学生信息
            List<Student> students = clazz.getStudents();
            students.forEach(student -> log.info("学生姓名：{}, 家乡：{}", student.getStudentName(), student.getHometown()));
        } else {
            log.info("未找到班级信息");
        }
        log.info("班级名称： {}" , clazz.getClazzName());

        log.info("任课老师： {}",  clazz.getTeacher().getTeacherName());
        log.info("班级学生信息如下： ");
        clazz.getStudents().forEach(student -> log.info("{} , {}" , student.getStudentName() , student.getHometown()));

    }


}