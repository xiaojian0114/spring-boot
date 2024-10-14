package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Clazz;
import org.example.entity.Teacher;

public interface ClazzMapper extends BaseMapper<Clazz> {
    @Select("SELECT * FROM teacher WHERE id = #{teacherId}")
    Teacher selectTeacherByClazzId(Long teacherId);
}
