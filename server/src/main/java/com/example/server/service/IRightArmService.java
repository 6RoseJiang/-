package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RightArm;
import java.util.List;


public interface IRightArmService extends IService<RightArm> {
    void saveRightArm(RightArm rightArm);

    void deleteRightArm(Integer id);

    void updateRightArm(RightArm rightArm);

    void insertRightArm(RightArm rightArm);
}
