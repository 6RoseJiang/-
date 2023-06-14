package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.exception.FlowException;
import com.example.server.mapper.FlowMapper;
import com.example.server.mapper.LoginMapper;
import com.example.server.pojo.Flow;
import com.example.server.pojo.LoginInformation;
import com.example.server.service.IFlowService;
import com.example.server.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, LoginInformation> implements ILoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public void saveUser(LoginInformation loginInformation) {
        int row = loginMapper.insert(loginInformation);
    }
}
