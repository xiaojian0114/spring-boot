package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class Student {
    private Long id;
    private String name;
    private Long clazzId;
    // ⽤于⼀对多映射
    @TableField(exist = false)
    private List<Course> courses;
}
