package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.EnvironmentalMapper;
import com.example.server.pojo.Environmental;
import com.example.server.service.IEnvironmentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentalServiceImpl extends ServiceImpl<EnvironmentalMapper, Environmental> implements IEnvironmentalService {

    @Autowired
    private EnvironmentalMapper environmentalMapper;

    @Override
    public void saveEnvironmental(Environmental environmental) {
        int row = environmentalMapper.insert(environmental);
    }

    @Override
    public void deleteEnvironmental(Integer id) {
        int row = environmentalMapper.deleteById(id);
    }

    @Override
    public void updateEnvironmental(Environmental environmental) {
        int row = environmentalMapper.updateById(environmental);
    }

    @Override
    public void insertEnvironmental(Environmental environmental) {
        environmentalMapper.insert(environmental);
    }

}
