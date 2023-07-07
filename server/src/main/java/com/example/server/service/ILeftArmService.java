package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.LeftArm;
import java.util.List;


public interface ILeftArmService extends IService<LeftArm> {
    void saveLeftArm(LeftArm leftArm);

    void deleteLeftArm(Integer id);

    void updateLeftArm(LeftArm leftArm);

    void insertLeftArm(LeftArm leftArm);


}
