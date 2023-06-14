package com.example.server.vo;

import lombok.Data;

import java.util.List;

@Data
public class FlowVo {
    private int id;
    private String type;
    private int inNumber;
    private int outNumber;
    private int totalNumber;
    private String date;
    private List pics;
}
