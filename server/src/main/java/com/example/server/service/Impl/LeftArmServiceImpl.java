package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.LeftArmMapper;
import com.example.server.pojo.LeftArm;
import com.example.server.service.ILeftArmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeftArmServiceImpl extends ServiceImpl<LeftArmMapper, LeftArm> implements ILeftArmService {

    @Autowired
    private LeftArmMapper leftArmMapper;

    @Override
    public void saveLeftArm(LeftArm leftArm) {
        int row = leftArmMapper.insert(leftArm);
    }

    @Override
    public void deleteLeftArm(Integer id) {
        int row = leftArmMapper.deleteById(id);
    }

    @Override
    public void updateLeftArm(LeftArm leftArm) {
        int row = leftArmMapper.updateById(leftArm);
    }

    @Override
    public void insertLeftArm(LeftArm leftArm) {
        leftArmMapper.insert(leftArm);
    }

}
