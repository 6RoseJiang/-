package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.CenterDataMapper;
import com.example.server.pojo.CenterData;
import com.example.server.service.ICenterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterDataServiceImpl extends ServiceImpl<CenterDataMapper, CenterData> implements ICenterDataService {

    @Autowired
    private CenterDataMapper centerDataMapper;

    @Override
    public void saveCenterData(CenterData centerData) {
        int row = centerDataMapper.insert(centerData);
    }

    @Override
    public void deleteCenterData(Integer id) {
        int row = centerDataMapper.deleteById(id);
    }

    @Override
    public void updateCenterData(CenterData centerData) {
        int row = centerDataMapper.updateById(centerData);
    }

    @Override
    public void insertCenterData(CenterData centerData) {
        centerDataMapper.insert(centerData);
    }

}
