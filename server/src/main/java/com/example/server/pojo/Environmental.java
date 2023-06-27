package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Environmental_table")
public class Environmental {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String Timestamp;
    private double temperature;
    private double pressure;
    private double humidity;
    private double gas;
}