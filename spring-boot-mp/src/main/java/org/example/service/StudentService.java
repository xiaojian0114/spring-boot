package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.mapper.StudentCourseMapper;
import org.example.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;
    private final StudentCourseMapper studentCourseMapper;
    /**
     * 根据学⽣ID获取该学⽣的课程信息
     */
    public Student getStudentWithCourses(Long studentId) {
        Student student = studentMapper.selectById(studentId);
        if (student != null) {
            List<Course> courses = studentCourseMapper.selectCoursesByStudentId(studentId);
            student.setCourses(courses);
        }
        return student;
    }
}
