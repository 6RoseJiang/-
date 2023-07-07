package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.LeftAnkleMapper;
import com.example.server.pojo.LeftAnkle;
import com.example.server.service.ILeftAnkleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeftAnkleServiceImpl extends ServiceImpl<LeftAnkleMapper, LeftAnkle> implements ILeftAnkleService {

    @Autowired
    private LeftAnkleMapper leftAnkleMapper;

    @Override
    public void saveLeftAnkle(LeftAnkle leftAnkle) {
        int row = leftAnkleMapper.insert(leftAnkle);
    }

    @Override
    public void deleteLeftAnkle(Integer id) {
        int row = leftAnkleMapper.deleteById(id);
    }

    @Override
    public void updateLeftAnkle(LeftAnkle leftAnkle) {
        int row = leftAnkleMapper.updateById(leftAnkle);
    }

    @Override
    public void insertLeftAnkle(LeftAnkle leftAnkle) {
        leftAnkleMapper.insert(leftAnkle);
    }

}
