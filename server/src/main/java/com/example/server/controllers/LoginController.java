package com.example.server.controllers;

import com.example.server.pojo.LoginInformation;
import com.example.server.service.ILoginService;
import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;
    @PostMapping("/submit")
    public Result add(HttpServletRequest request, @RequestBody LoginInformation loginInformation){

        log.info("user added, information={}", loginInformation);
        loginService.saveUser(loginInformation);
        return Result.success();
    }
}
