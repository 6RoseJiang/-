package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.LeftAnkle;
import java.util.List;


public interface ILeftAnkleService extends IService<LeftAnkle> {
    void saveLeftAnkle(LeftAnkle leftAnkle);

    void deleteLeftAnkle(Integer id);

    void updateLeftAnkle(LeftAnkle leftAnkle);

    void insertLeftAnkle(LeftAnkle leftAnkle);
}
