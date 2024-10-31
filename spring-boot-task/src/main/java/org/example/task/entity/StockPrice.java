package org.example.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("stock_price")
public class StockPrice {

    @TableId(type = IdType.AUTO)
    private Long id;


    private String name;
    private Double price;
    private LocalDateTime updateTime;
}
