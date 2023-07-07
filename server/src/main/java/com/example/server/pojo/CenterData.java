package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("CenterData")
public class CenterData {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String Timestamp;
    private double acc_x;
    private double acc_y;
    private double acc_z;
    private double gyro_x;
    private double gyro_y;
    private double gyro_z;
    private int HR;
    private int HR_flag;
    private int Seq;
}