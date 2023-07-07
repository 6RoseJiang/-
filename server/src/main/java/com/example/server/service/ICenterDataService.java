package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.CenterData;
import java.util.List;


public interface ICenterDataService extends IService<CenterData> {
    void saveCenterData(CenterData centerData);

    void deleteCenterData(Integer id);

    void updateCenterData(CenterData centerData);

    void insertCenterData(CenterData centerData);

    //void insertCenterData(CenterData centerData);centerDa
}
