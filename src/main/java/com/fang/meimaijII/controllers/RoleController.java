package com.fang.meimaijII.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.services.RoleService;
import com.fang.meimaijII.utils.ResponseSpec;
import com.fang.meimaijII.vos.RoleVo;

@RestController
@RequestMapping("/role")
public class RoleController{

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseSpec create(@Valid @RequestBody RoleVo vo){
        roleService.create(vo);
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PatchMapping("/{id}")
    public ResponseSpec upate(@PathVariable(name = "id", required = true) Long roleId, @RequestBody RoleVo vo){
        roleService.update(roleId, vo);
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/{id}")
    public ResponseSpec get(@PathVariable(name = "id", required = true) Long roleId){
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), roleService.get(roleId));
    }

    @GetMapping
    public ResponseSpec list(){
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), roleService.list());
    }

    @DeleteMapping("/{id}")
    public ResponseSpec delete(Long roleId){
        roleService.delete(roleId);
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }
}
