package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.RightArmMapper;
import com.example.server.pojo.RightArm;
import com.example.server.service.IRightArmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RightArmServiceImpl extends ServiceImpl<RightArmMapper, RightArm> implements IRightArmService {

    @Autowired
    private RightArmMapper rightArmMapper;

    @Override
    public void saveRightArm(RightArm rightArm) {
        int row = rightArmMapper.insert(rightArm);
    }

    @Override
    public void deleteRightArm(Integer id) {
        int row = rightArmMapper.deleteById(id);
    }

    @Override
    public void updateRightArm(RightArm rightArm) {
        int row = rightArmMapper.updateById(rightArm);
    }

    @Override
    public void insertRightArm(RightArm rightArm) {
        rightArmMapper.insert(rightArm);
    }

}
