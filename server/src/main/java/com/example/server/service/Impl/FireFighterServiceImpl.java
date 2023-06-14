package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.FireFighterMapper;
import com.example.server.pojo.FireFighterInformation;
import com.example.server.service.IFireFighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireFighterServiceImpl extends ServiceImpl<FireFighterMapper, FireFighterInformation> implements IFireFighterService {

    @Autowired
    private FireFighterMapper firefighterMapper;

    @Override
    public void saveFireFighter(FireFighterInformation firefighterInformation) {
        int row = firefighterMapper.insert(firefighterInformation);
    }

    @Override
    public void deleteFireFighter(Integer id) {
        int row = firefighterMapper.deleteById(id);
    }

    @Override
    public void updateFireFighter(FireFighterInformation firefighterInformation) {
        int row = firefighterMapper.updateById(firefighterInformation);
    }

    @Override
    public void insertFireFighter(FireFighterInformation firefighterInformation) {
        firefighterMapper.insert(firefighterInformation);
    }

}
