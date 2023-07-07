package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.pojo.LeftAnkle;
import com.example.server.service.ILeftAnkleService;
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
@RequestMapping("/leftankle")
public class LeftAnkleController {

    @Autowired
    private ILeftAnkleService leftAnkleService;
    @PostMapping("/addLeftAnkle")
    public Result add(HttpServletRequest request, @RequestBody LeftAnkle leftAnkle){

        log.info("leftankle added, information={}", leftAnkle);
        leftAnkleService.saveLeftAnkle(leftAnkle);
        return Result.success();
    }

    @GetMapping("/listLeftAnkle")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("LeftAnkle list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<LeftAnkle> page = new Page<>(pageNum, pageSize);
        IPage<LeftAnkle> pageResult = leftAnkleService.page(page);

        List<LeftAnkle> LeftAnkleList = pageResult.getRecords();
        List voList = new ArrayList<>();
        for (LeftAnkle leftAnkle: LeftAnkleList){
            FirefighterVo fireFighterVo = new FirefighterVo();
            BeanUtils.copyProperties(leftAnkle, fireFighterVo);

            voList.add(fireFighterVo);
        }

        List leftAnkleList = pageResult.getRecords().stream().map(leftAnkle -> {
            FirefighterVo firefighterVo = new FirefighterVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("id", leftAnkle.getId());
            BeanUtils.copyProperties(leftAnkle, firefighterVo);
            return firefighterVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(leftAnkleList);

        return Result.success(pageResult);
    }

    @DeleteMapping("/deleteLeftAnkle/{id}")
    public Result delete(@PathVariable int id){
        log.info("LeftAnkle delete, userId = {}", id);
        leftAnkleService.deleteLeftAnkle(id);
        return Result.success();
    }
}
