package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("flow_table")
public class Flow {
    private Integer id;
    private String type;
    private int inNumber;
    private int outNumber;
    private int totalNumber;

    private String date;
}
