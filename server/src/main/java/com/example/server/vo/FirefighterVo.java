package com.example.server.vo;

import lombok.Data;

import java.util.List;

@Data
public class FirefighterVo {
    private int id;
    private String firefighterId;
    private String name;
    private String deviceStatus;
    private String initPos;
}
