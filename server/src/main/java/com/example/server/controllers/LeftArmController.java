package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.pojo.LeftArm;
import com.example.server.service.ILeftArmService;
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
@RequestMapping("/leftarm")
public class LeftArmController {

    @Autowired
    private ILeftArmService leftArmService;
    @PostMapping("/addLeftArm")
    public Result add(HttpServletRequest request, @RequestBody LeftArm leftArm){

        log.info("leftarm added, information={}", leftArm);
        leftArmService.saveLeftArm(leftArm);
        return Result.success();
    }

    @GetMapping("/listLeftArm")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("LeftArm list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<LeftArm> page = new Page<>(pageNum, pageSize);
        IPage<LeftArm> pageResult = leftArmService.page(page);

        List<LeftArm> LeftArmList = pageResult.getRecords();
        List voList = new ArrayList<>();
        for (LeftArm leftArm: LeftArmList){
            FirefighterVo fireFighterVo = new FirefighterVo();
            BeanUtils.copyProperties(leftArm, fireFighterVo);

            voList.add(fireFighterVo);
        }

        List leftArmList = pageResult.getRecords().stream().map(leftArm -> {
            FirefighterVo firefighterVo = new FirefighterVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("id", leftArm.getId());
            BeanUtils.copyProperties(leftArm, firefighterVo);
            return firefighterVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(leftArmList);

        return Result.success(pageResult);
    }

    @DeleteMapping("/deleteLeftArm/{id}")
    public Result delete(@PathVariable int id){
        log.info("LeftArm delete, userId = {}", id);
        leftArmService.deleteLeftArm(id);
        return Result.success();
    }
}
