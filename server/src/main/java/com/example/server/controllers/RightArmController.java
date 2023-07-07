package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.pojo.RightArm;
import com.example.server.service.IRightArmService;
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
@RequestMapping("/rightarm")
public class RightArmController {

    @Autowired
    private IRightArmService rightArmService;
    @PostMapping("/addRightArm")
    public Result add(HttpServletRequest request, @RequestBody RightArm rightArm){

        log.info("rightarm added, information={}", rightArm);
        rightArmService.saveRightArm(rightArm);
        return Result.success();
    }

    @GetMapping("/listRightArm")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("RightArm list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<RightArm> page = new Page<>(pageNum, pageSize);
        IPage<RightArm> pageResult = rightArmService.page(page);

        List<RightArm> RightArmList = pageResult.getRecords();
        List voList = new ArrayList<>();
        for (RightArm rightArm: RightArmList){
            FirefighterVo fireFighterVo = new FirefighterVo();
            BeanUtils.copyProperties(rightArm, fireFighterVo);

            voList.add(fireFighterVo);
        }

        List rightArmList = pageResult.getRecords().stream().map(rightArm -> {
            FirefighterVo firefighterVo = new FirefighterVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("id", rightArm.getId());
            BeanUtils.copyProperties(rightArm, firefighterVo);
            return firefighterVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(rightArmList);

        return Result.success(pageResult);
    }

    @DeleteMapping("/deleteRightArm/{id}")
    public Result delete(@PathVariable int id){
        log.info("RightArm delete, userId = {}", id);
        rightArmService.deleteRightArm(id);
        return Result.success();
    }
}
