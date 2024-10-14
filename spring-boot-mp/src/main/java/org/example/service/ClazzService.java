package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Clazz;
import org.example.entity.Teacher;
import org.example.mapper.ClazzMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClazzService {
    private final ClazzMapper clazzMapper;


    public Clazz getClazzWithTeacher(Long clazzId) {

        Clazz clazz = clazzMapper.selectById(clazzId);

        if (clazz != null) {
            Teacher teacher = clazzMapper.selectTeacherByClazzId(clazz.getTeacherId());
            clazz.setTeacher(teacher);
        }
        return clazz;
    }
}
