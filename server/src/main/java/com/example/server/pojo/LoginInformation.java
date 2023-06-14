package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("login_table")
public class LoginInformation {
    private Integer id;
    private String userName;
    private String password;
}
