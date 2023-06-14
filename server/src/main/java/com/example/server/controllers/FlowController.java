package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.exception.FlowException;
import com.example.server.form.FlowForm;
import com.example.server.pojo.Flow;
import com.example.server.pojo.Pic;
import com.example.server.service.IFlowService;
import com.example.server.service.IPicService;
import com.example.server.vo.FlowVo;
import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/flow")
public class FlowController {

    @Autowired
    private IFlowService flowService;

    @Autowired
    private IPicService picService;

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("flow list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<Flow> page = new Page<Flow>(pageNum, pageSize);
        IPage<Flow> pageResult = flowService.page(page);

//        List<Poi> poiList = pageResult.getRecords();
//        List voList = new ArrayList<>();
//        for (Poi poi: poiList){
//            PoiVo poiVo = new PoiVo();
//            BeanUtils.copyProperties(poi, poiVo);
//
//            voList.add(poiVo);
//        }

        List flowList = pageResult.getRecords().stream().map(flow -> {
            FlowVo flowVo = new FlowVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("flow_id", flow.getId());
            List<Pic> pics = picService.list(query);
            BeanUtils.copyProperties(flow, flowVo);
            flowVo.setPics(pics);
            return flowVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(flowList);

        return Result.success(pageResult);
    }
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable int id){
        log.info("flow detail, id = {}", id);

        FlowVo flowVo = new FlowVo();
        Flow flow = flowService.getById(id);
        if(flow == null){
            throw FlowException.NotFound();
        }
        QueryWrapper query = new QueryWrapper();
        query.eq("flow_id", flow.getId());
//        List<Pic> pics = picService.list(query);
        BeanUtils.copyProperties(flow, flowVo);
//        flowVo.setPics(pics);
        return Result.success(flowVo);
    }

    @PostMapping("/add")
    public Result add(@RequestBody FlowForm flowForm){
        log.info("flow add, flow={}", flowForm);
        Flow flow = new Flow();
        BeanUtils.copyProperties(flowForm, flow);
        flowService.saveMain(flow, flowForm.getPics());

        return detail(flow.getId());
    }

    @PutMapping("/edit/{id}")
    public Result edit(@RequestBody FlowForm flowForm, @PathVariable int id){
        log.info("flow edit, flow={}", flowForm);
        Flow flow = new Flow();
        BeanUtils.copyProperties(flowForm, flow);
        flow.setId(id);
        flowService.updateMain(flow, flowForm.getPics());
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        log.info("flow delete, id = {}", id);
        flowService.deleteMain(id);
        return Result.success();
    }

    @GetMapping("/total/{id}")
    public Result total(@PathVariable int id){
        FlowVo flowVo = new FlowVo();
        List<Flow> list = flowService.list();
        Flow flow1 = list.get(list.size()-1);
        System.out.println(flow1);
        BeanUtils.copyProperties(flow1, flowVo);
        return Result.success(flowVo);
    }
}
