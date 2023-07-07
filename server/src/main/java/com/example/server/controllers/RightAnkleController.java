package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.pojo.RightAnkle;
import com.example.server.service.IRightAnkleService;
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
public class RightAnkleController {

    @Autowired
    private IRightAnkleService rightAnkleService;
    @PostMapping("/addRightAnkle")
    public Result add(HttpServletRequest request, @RequestBody RightAnkle rightAnkle){

        log.info("rightankle added, information={}", rightAnkle);
        rightAnkleService.saveRightAnkle(rightAnkle);
        return Result.success();
    }

    @GetMapping("/listRightAnkle")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("rightankle list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<RightAnkle> page = new Page<>(pageNum, pageSize);
        IPage<RightAnkle> pageResult = rightAnkleService.page(page);

        List<RightAnkle> RightAnkleList = pageResult.getRecords();
        List voList = new ArrayList<>();
        for (RightAnkle rightAnkle: RightAnkleList){
            FirefighterVo fireFighterVo = new FirefighterVo();
            BeanUtils.copyProperties(rightAnkle, fireFighterVo);

            voList.add(fireFighterVo);
        }

        List rightAnkleList = pageResult.getRecords().stream().map(rightAnkle -> {
            FirefighterVo firefighterVo = new FirefighterVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("id", rightAnkle.getId());
            BeanUtils.copyProperties(rightAnkle, firefighterVo);
            return firefighterVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(rightAnkleList);

        return Result.success(pageResult);
    }

    @DeleteMapping("/deleteRightAnkle/{id}")
    public Result delete(@PathVariable int id){
        log.info("RightAnkle delete, userId = {}", id);
        rightAnkleService.deleteRightAnkle(id);
        return Result.success();
    }
}
