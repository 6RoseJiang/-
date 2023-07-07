package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.GaitDataMapper;
import com.example.server.pojo.GaitData;
import com.example.server.service.IGaitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GaitDataServiceImpl extends ServiceImpl<GaitDataMapper, GaitData> implements IGaitDataService {

    @Autowired
    private GaitDataMapper gaitDataMapper;

    @Override
    public void saveGaitData(GaitData gaitData) {
        int row = gaitDataMapper.insert(gaitData);
    }

    @Override
    public void deleteGaitData(Integer id) {
        int row = gaitDataMapper.deleteById(id);
    }

    @Override
    public void updateGaitData(GaitData gaitData) {
        int row = gaitDataMapper.updateById(gaitData);
    }

    @Override
    public void insertGaitData(GaitData gaitData) {
        gaitDataMapper.insert(gaitData);
    }

}
