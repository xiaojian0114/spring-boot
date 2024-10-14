package org.example.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Course;
import org.example.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudentMapperTest {

    @Resource
    private StudentMapper studentMapper;

    //插入学生
    @Test
    void insert() {
        Student student = Student.builder().clazzId(1).studentName("测试学生1").hometown("江苏南京").birthday(LocalDate.now()).build();
        int n = studentMapper.insert(student);
        assertEquals(1, n);
    }

    //找学生
    @Test
    void findStudentById() {
        Student student = studentMapper.findStudentById(1001);
        assertEquals("钱智康" , student.getStudentName());
    }

    //更新名字
    @Test
    void updateById() {
        Student student = Student.builder().studentId(1001).studentName("新的名字1").build();
        int n = studentMapper.updateById(student);
        assertEquals(1, n);
    }

    //删除通过id
    @Test
    void deleteById() {
        int n = studentMapper.deleteById(1004);
        assertEquals(1, n);
    }


    //批量添加
    @Test
    void batchInsert() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = Student.builder()
                    .studentId(8020 + i)
                    .clazzId(1)
                    .studentName("测试学生" + i)
                    .hometown("江苏南京")
                    .birthday(LocalDate.now())
                    .build();
            students.add(student);
        }
        int n = studentMapper.batchInsert(students);
        assertEquals(10, n);
    }

    //批量删除
    @Test
    void batchDelete() {

        List<Integer> idList = List.of(8021 ,8022,8023);
        int n = studentMapper.batchDelete(idList);
        assertEquals(3, n);
    }

    @Test
    void batchUpdate() {
        // 准备测试数据
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = Student.builder()
                    .studentId(8000 + i)
                    .clazzId(2 + i % 3) // 假设更新为不同的班级ID
                    .studentName("更新后的学生" + i)
                    .hometown("更新后的家乡" + i)
                    .birthday(LocalDate.now().minusDays(i * 10)) // 假设更新为不同的生日
                    .build();
            students.add(student);
        }


        int n = studentMapper.batchUpdate(students);
    }

    @Test
    void selectByDynamicSql() {

        Student student = Student.builder().hometown("州").build();
        List<Student> students = studentMapper.selectByDynamicSql(student);
        students.forEach(System.out::println);
    }




    @Test
    void getStudentManyToOne(){
        Student student = studentMapper.getStudentManyToOne(1001);
        log.info("{},{},{}" , student.getStudentName() , student.getHometown() , student.getClazz().getClazzName());
    }

    @Test
    void getStudent() {
        Student student = studentMapper.getStudent(1001);
        log.info("{} , {}" , student.getStudentName() , student.getHometown());
        log.info("{}" , student.getClazz().getClazzName());
        List<Course> courses = student.getCourses();
        courses.forEach(course -> log.info("{},{} " ,course.getCourseId(), course.getCourseName()));
    }
}