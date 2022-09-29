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
import com.fang.meimaijII.services.CategoryService;
import com.fang.meimaijII.utils.ResponseSpec;
import com.fang.meimaijII.vos.CategoryVo;

@RestController
@RequestMapping("/category")
public class CategoryController{

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseSpec create(@Valid @RequestBody CategoryVo vo){
        categoryService.create(vo);
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PatchMapping("/{nameEn}")
    public ResponseSpec update(@PathVariable(name = "nameEn", required = true) String nameEn, @RequestBody CategoryVo vo){
        categoryService.update(nameEn, vo);
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping
    public ResponseSpec list(){
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), categoryService.list());
    }

    @DeleteMapping("/{nameEn}")
    public ResponseSpec delete(@PathVariable(name = "nameEn", required = true) String nameEn){
        categoryService.delete(nameEn);
        return new ResponseSpec(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }
    
    // category 對應的產品列表
}
