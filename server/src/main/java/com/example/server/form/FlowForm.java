package com.example.server.form;

import com.example.server.pojo.Pic;
import lombok.Data;

import java.util.List;

@Data
public class FlowForm {
    private Integer id;
    private String type;
    private int inNumber;
    private int outNumber;
    private int totalNumber;
    private String date;

    public FlowForm(Integer id, String type, int inNumber, int outNumber, int totalNumber, String date) {
        this.id = id;
        this.type = type;
        this.inNumber = inNumber;
        this.outNumber = outNumber;
        this.totalNumber = totalNumber;
        this.date = date;
    }

    private List<Pic> pics;
}
