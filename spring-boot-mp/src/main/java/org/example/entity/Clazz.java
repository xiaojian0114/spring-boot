package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Clazz {
    private Long id;
    private String name;
    private Long teacherId;
    // ⽤于⼀对⼀映射
    @TableField(exist = false)
    private Teacher teacher;
}
