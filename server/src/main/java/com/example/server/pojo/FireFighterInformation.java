package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("firefighter_table")
public class FireFighterInformation {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String firefighterId;
    private String name;
    private String deviceStatus;
    private String initPos;
}
