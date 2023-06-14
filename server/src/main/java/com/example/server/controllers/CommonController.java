package com.example.server.controllers;

import com.example.server.service.IStorageService;
import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/")
public class CommonController {

    @Autowired
    private IStorageService storageService;

    @Value("${upload.accessPath}")
    private String accessPath;

    @Value("${upload.localPath}")
    private String localPath;

    @PostMapping(value = "/upload")
    public com.example.server.vo.Result upload(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String fileName = file.getOriginalFilename();
        log.info("Upload file, fileName = {}", fileName);
        storageService.save(file, fileName, localPath);
        return Result.success(accessPath + fileName);
    }
}
