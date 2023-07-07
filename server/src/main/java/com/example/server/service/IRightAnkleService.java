package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RightAnkle;
import java.util.List;


public interface IRightAnkleService extends IService<RightAnkle> {
    void saveRightAnkle(RightAnkle rightAnkle);

    void deleteRightAnkle(Integer id);

    void updateRightAnkle(RightAnkle rightAnkle);

    void insertRightAnkle(RightAnkle rightAnkle);
}
