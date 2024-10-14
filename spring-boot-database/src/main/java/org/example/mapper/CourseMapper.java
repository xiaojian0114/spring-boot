package org.example.mapper;

import org.example.entity.Course;

import java.util.List;

public interface CourseMapper {
    /**
     * 查询所有课程（关联查询出每⻔课程的选课学⽣）
     * @return List<Course>
     */
    List<Course> selectAll();

    Course getCourseWithStudents(int courseId);
}