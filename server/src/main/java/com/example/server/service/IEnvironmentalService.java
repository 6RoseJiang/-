package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Environmental;
import com.example.server.pojo.FireFighterInformation;
import java.util.List;


public interface IEnvironmentalService extends IService<Environmental> {
    void saveEnvironmental(Environmental environmental);

    void deleteEnvironmental(Integer id);

    void updateEnvironmental(Environmental environmental);

    void insertEnvironmental(Environmental environmental);
}
