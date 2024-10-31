package org.example.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.task.entity.Student;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
