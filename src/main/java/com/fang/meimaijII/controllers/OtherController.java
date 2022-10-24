package com.fang.meimaijII.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.services.OtherService;
import com.fang.meimaijII.utils.ResponseSpec;
import com.fang.meimaijII.vos.LoginVo;

@RestController
public class OtherController{

    @Autowired
    private OtherService otherService;

    @GetMapping("/health")
    public ResponseEntity<Object> health(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseSpec upload(@RequestParam(name = "files") MultipartFile[] files){
        otherService.upload(files);
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/module")
    public ResponseSpec getModules(){
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), otherService.getModules());
    }

    @PostMapping("/login")
    public ResponseSpec login(@Valid @RequestBody LoginVo vo){
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), otherService.login(vo));
    }

    @PostMapping("/verify")
    public ResponseSpec verify(HttpServletRequest request){
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), otherService.verify(request));
    }
    
    @PostMapping("/refresh")
    public ResponseSpec refresh(@RequestParam(name = "refreshToken") String refreshToken) {
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), otherService.refresh(refreshToken));
    }
    
}
