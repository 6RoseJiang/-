package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.GaitData;
import java.util.List;


public interface IGaitDataService extends IService<GaitData> {
    void saveGaitData(GaitData gaitData);

    void deleteGaitData(Integer id);

    void updateGaitData(GaitData gaitData);

    void insertGaitData(GaitData gaitData);
}
