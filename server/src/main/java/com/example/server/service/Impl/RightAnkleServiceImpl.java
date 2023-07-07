package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.RightAnkleMapper;
import com.example.server.pojo.RightAnkle;
import com.example.server.service.IRightAnkleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RightAnkleServiceImpl extends ServiceImpl<RightAnkleMapper, RightAnkle> implements IRightAnkleService {

    @Autowired
    private RightAnkleMapper rightAnkleMapper;

    @Override
    public void saveRightAnkle(RightAnkle rightAnkle) {
        int row = rightAnkleMapper.insert(rightAnkle);
    }

    @Override
    public void deleteRightAnkle(Integer id) {
        int row = rightAnkleMapper.deleteById(id);
    }

    @Override
    public void updateRightAnkle(RightAnkle rightAnkle) {
        int row = rightAnkleMapper.updateById(rightAnkle);
    }

    @Override
    public void insertRightAnkle(RightAnkle rightAnkle) {
        rightAnkleMapper.insert(rightAnkle);
    }

}
