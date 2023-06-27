package com.example.server.controllers;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.pojo.CenterData;
import com.example.server.pojo.FireFighterInformation;
import com.example.server.service.ICenterDataService;
import com.example.server.service.IFireFighterService;
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
@RequestMapping("/centerdata")
public class CenterDataController {

    @Autowired
    private ICenterDataService centerDataService;
    @PostMapping("/addCenterData")
    public Result add(HttpServletRequest request, @RequestBody CenterData centerData){

        log.info("centerdata added, information={}", centerData);
        centerDataService.saveCenterData(centerData);
        return Result.success();
    }

    @GetMapping("/listCenterData")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("centerdata list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<CenterData> page = new Page<>(pageNum, pageSize);
        IPage<CenterData> pageResult = centerDataService.page(page);

        List<CenterData> CenterDataList = pageResult.getRecords();
        List voList = new ArrayList<>();
        for (CenterData centerData: CenterDataList){
            FirefighterVo fireFighterVo = new FirefighterVo();
            BeanUtils.copyProperties(centerData, fireFighterVo);

            voList.add(fireFighterVo);
        }

        List centerDataList = pageResult.getRecords().stream().map(centerData -> {
            FirefighterVo firefighterVo = new FirefighterVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("id", centerData.getId());
            BeanUtils.copyProperties(centerData, firefighterVo);
            return firefighterVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(centerDataList);

        return Result.success(pageResult);
    }

    @DeleteMapping("/deleteCenterData/{id}")
    public Result delete(@PathVariable int id){
        log.info("FireFighter delete, userId = {}", id);
        centerDataService.deleteCenterData(id);
        return Result.success();
    }

}
