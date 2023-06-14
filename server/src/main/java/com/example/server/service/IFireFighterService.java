package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.FireFighterInformation;
import java.util.List;


public interface IFireFighterService extends IService<FireFighterInformation> {
    void saveFireFighter(FireFighterInformation firefighterInformation);

    void deleteFireFighter(Integer id);

    void updateFireFighter(FireFighterInformation firefighterInformation);

    void insertFireFighter(FireFighterInformation firefighterInformation);
}
