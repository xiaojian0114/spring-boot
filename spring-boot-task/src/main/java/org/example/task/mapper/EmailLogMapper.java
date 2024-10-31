package org.example.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.task.entity.EmailLog;

@Mapper
public interface EmailLogMapper extends BaseMapper<EmailLog> {
}
