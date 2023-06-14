package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.pojo.FireFighterInformation;
import com.example.server.service.IFireFighterService;
import com.example.server.vo.FirefighterVo;
import com.example.server.vo.Result;
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
@RequestMapping("/firefighter")
public class FireFighterController {

    @Autowired
    private IFireFighterService fireFighterService;
    @PostMapping("/addFireFighter")
    public Result add(HttpServletRequest request, @RequestBody FireFighterInformation fireFighterInformation){

        log.info("firefighter added, information={}", fireFighterInformation);
        fireFighterService.saveFireFighter(fireFighterInformation);
        return Result.success();
    }

    @GetMapping("/listFireFighter")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        log.info("firefighter list, pageNum = {}, pageSize = {}", pageNum, pageSize);
        Page<FireFighterInformation> page = new Page<>(pageNum, pageSize);
        IPage<FireFighterInformation> pageResult = fireFighterService.page(page);

        List<FireFighterInformation> FireFighterInformationList = pageResult.getRecords();
        List voList = new ArrayList<>();
        for (FireFighterInformation fireFighterInformation: FireFighterInformationList){
            FirefighterVo fireFighterVo = new FirefighterVo();
            BeanUtils.copyProperties(fireFighterInformation, fireFighterVo);

            voList.add(fireFighterVo);
        }

        List fireFighterList = pageResult.getRecords().stream().map(fireFighterInformation -> {
            FirefighterVo firefighterVo = new FirefighterVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("firefighter_id", fireFighterInformation.getFirefighterId());
            BeanUtils.copyProperties(fireFighterInformation, firefighterVo);
            return firefighterVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(fireFighterList);

        return Result.success(pageResult);
    }

    @DeleteMapping("/deleteFireFighter/{id}")
    public Result delete(@PathVariable int id){
        log.info("FireFighter delete, userId = {}", id);
        fireFighterService.deleteFireFighter(id);
        return Result.success();
    }
}
