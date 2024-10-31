package org.example.task.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_teacher")
public class Teacher {

    @ExcelProperty("老师id")
    private Long id;

    @ExcelProperty("老师姓名")
    private String name;


}
