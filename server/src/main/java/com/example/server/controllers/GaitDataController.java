package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.pojo.GaitData;
import com.example.server.service.IGaitDataService;
import com.example.server.vo.FirefighterVo;
import
        com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@Slf4j
@RequestMapping("/rightankle")
public class GaitDataController {

    @Autowired
    private IGaitDataService gaitDataService;
    @PostMapping("/addGaitData")
    public Result add(HttpServletRequest request, @RequestBody GaitData gaitData){

        log.info("gaitdata added, information={}", gaitData);
        gaitDataService.saveGaitData(gaitData);
        return Result.success();
    }

    @GetMapping("/listGaitData")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("gaitdata list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<GaitData> page = new Page<>(pageNum, pageSize);
        IPage<GaitData> pageResult = gaitDataService.page(page);

        List<GaitData> GaitDataList = pageResult.getRecords();
        List voList = new ArrayList<>();
        for (GaitData gaitData: GaitDataList){
            FirefighterVo fireFighterVo = new FirefighterVo();
            BeanUtils.copyProperties(gaitData, fireFighterVo);

            voList.add(fireFighterVo);
        }

        List gaitdataList = pageResult.getRecords().stream().map(gaitData -> {
            FirefighterVo firefighterVo = new FirefighterVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("id", gaitData.getId());
            BeanUtils.copyProperties(gaitData, firefighterVo);
            return firefighterVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(gaitdataList);

        return Result.success(pageResult);
    }

    @DeleteMapping("/deleteGaitData/{id}")
    public Result delete(@PathVariable int id){
        log.info("GaitData delete, userId = {}", id);
        gaitDataService.deleteGaitData(id);
        return Result.success();
    }
}
