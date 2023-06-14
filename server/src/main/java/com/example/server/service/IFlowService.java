package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Flow;
import com.example.server.pojo.Pic;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public interface IFlowService extends IService<Flow> {
    void saveMain(Flow flow, List<Pic> pics);

    void deleteMain(Integer id);

    void updateMain(Flow flow, List<Pic> pics);

    void insertTotal(int totalNumber);
}
