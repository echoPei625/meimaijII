package com.fang.meimaijII.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.services.UserService;
import com.fang.meimaijII.utils.ResponseSpec;
import com.fang.meimaijII.vos.UserVo;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    
    @PostMapping
    public ResponseSpec create(@Valid @RequestBody UserVo vo){
        userService.create(vo);
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }
}
