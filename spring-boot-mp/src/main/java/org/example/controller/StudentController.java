package org.example.controller;


import lombok.AllArgsConstructor;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    /**
     * 获取学⽣及其课程信息
     */
    @GetMapping("/{id}/courses")
    public Student getStudentWithCourses(@PathVariable Long id) {
        return studentService.getStudentWithCourses(id);
    }
}
