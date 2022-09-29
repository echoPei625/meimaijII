package com.fang.meimaijII.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.services.OtherService;
import com.fang.meimaijII.utils.ResponseSpec;

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
}
