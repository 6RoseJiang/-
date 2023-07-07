package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.pojo.Environmental;
import com.example.server.service.IEnvironmentalService;
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
@RequestMapping("/environmnetal")
public class EnvironmentalController {

    @Autowired
    private IEnvironmentalService environmentalService;
    @PostMapping("/addEnvironmental")
    public Result add(HttpServletRequest request, @RequestBody Environmental environmental){

        log.info("environmental added, information={}", environmental);
        environmentalService.saveEnvironmental(environmental);
        return Result.success();
    }

    @GetMapping("/listEnvironmental")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("environmental list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<Environmental> page = new Page<>(pageNum, pageSize);
        IPage<Environmental> pageResult = environmentalService.page(page);

        List<Environmental> Environmentallist = pageResult.getRecords();
        List voList = new ArrayList<>();
        for (Environmental environmental: Environmentallist){
            FirefighterVo fireFighterVo = new FirefighterVo();
            BeanUtils.copyProperties(environmental, fireFighterVo);

            voList.add(fireFighterVo);
        }

        List environmentalList = pageResult.getRecords().stream().map(environmental -> {
            FirefighterVo firefighterVo = new FirefighterVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("id", environmental.getId());
            BeanUtils.copyProperties(environmental, firefighterVo);
            return firefighterVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(environmentalList);

        return Result.success(pageResult);
    }

    @DeleteMapping("/deleteEnvironmental/{id}")
    public Result delete(@PathVariable int id){
        log.info("Environmental delete, userId = {}", id);
        environmentalService.deleteEnvironmental(id);
        return Result.success();
    }
}
