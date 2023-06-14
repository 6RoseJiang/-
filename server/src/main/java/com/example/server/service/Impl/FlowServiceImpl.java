package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.exception.FlowException;
import com.example.server.mapper.FlowMapper;
import com.example.server.mapper.PicMapper;
import com.example.server.pojo.Flow;
import com.example.server.pojo.Pic;
import com.example.server.service.IFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowServiceImpl extends ServiceImpl<FlowMapper, Flow> implements IFlowService {

    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private PicMapper picMapper;

    @Override
    public void saveMain(Flow flow, List<Pic> pics) {
        int row = flowMapper.insert(flow);

        if(row == 0){
            throw FlowException.OperateFail();
        }

        if(pics != null){
            for (Pic pic: pics
                 ) {
                pic.setFlowId(flow.getId());
                row = picMapper.insert(pic);
                if(row == 0){
                    throw FlowException.OperateFail();
                }
            }
        }
    }

    @Override
    public void deleteMain(Integer id) {
        int row = flowMapper.deleteById(id);
        if(row == 0){
            throw FlowException.OperateFail();
        }
        row = picMapper.deleteByFlowId(id);
        if(row == 0){
            throw FlowException.OperateFail();
        }
    }

    @Override
    public void updateMain(Flow flow, List<Pic> pics) {
        int row = flowMapper.updateById(flow);
        if(row == 0){
            throw FlowException.OperateFail();
        }

        picMapper.deleteByFlowId(flow.getId());

        if(pics != null){
            for (Pic pic: pics
            ) {
                pic.setFlowId(flow.getId());
                row = picMapper.insert(pic);
                if(row == 0){
                    throw FlowException.OperateFail();
                }
            }
        }
    }

    @Override
    public void insertTotal(int totalNumber) {
    }

}
