package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("GaitData")
public class GaitData {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String Timestamp;
    private double acc_x_l;
    private double acc_y_l;
    private double acc_z_l;
    private double gyro_x_l;
    private double gyro_y_l;
    private double gyro_z_l;
    private double mag_x_l;
    private double mag_y_l;
    private double mag_z_l;
    private double acc_x_r;
    private double acc_y_r;
    private double acc_z_r;
    private double gyro_x_r;
    private double gyro_y_r;
    private double gyro_z_r;
    private double mag_x_r;
    private double mag_y_r;
    private double mag_z_r;
}
