package com.fang.meimaijII.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.meimaijII.entities.Category;
import com.fang.meimaijII.entities.Rough;
import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.repositories.CategoryRepository;
import com.fang.meimaijII.repositories.ProductRepository;
import com.fang.meimaijII.repositories.RoughRepository;
import com.fang.meimaijII.utils.MeimaijIIException;
import com.fang.meimaijII.vos.CategoryVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService{

    @Autowired
    private RoughRepository roughRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void create(CategoryVo vo){
        Rough rough = roughRepository.findByNameEn(vo.getRoughNameEn()).orElseThrow(() -> new MeimaijIIException("查無 " + vo.getRoughNameEn() + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (categoryRepository.findByNameEn(vo.getCategoryNameEn()).isPresent()){
            throw new MeimaijIIException("種類英文名稱已存在", ResponseCode.LOGIC_ISSUE.getCode());
        }
        Category category = Category.builder().roughId(rough.getId()).nameEn(vo.getCategoryNameEn()).nameZh(vo.getCategoryNameZh()).build();
        categoryRepository.save(category);
    }

    public void update(String nameEn, CategoryVo vo){
        Rough rough = null;
        Category category = categoryRepository.findByNameEn(nameEn).orElseThrow(() -> new MeimaijIIException("查無 " + nameEn + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (null != vo.getRoughNameEn()){
            rough = roughRepository.findByNameEn(vo.getRoughNameEn()).orElseThrow(() -> new MeimaijIIException("查無 " + vo.getRoughNameEn() + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
            category.setRoughId(rough.getId());
        }
        if (null != vo.getCategoryNameEn()){
            if (categoryRepository.findByNameEn(vo.getCategoryNameEn()).isPresent()){
                throw new MeimaijIIException("種類英文名稱已存在", ResponseCode.LOGIC_ISSUE.getCode());
            }
            category.setNameEn(vo.getCategoryNameEn());
        }
        if (null != vo.getCategoryNameZh()){
            category.setNameZh(vo.getCategoryNameZh());
        }
        categoryRepository.save(category);
    }

    public void delete(String nameEn){
        Category category = categoryRepository.findByNameEn(nameEn).orElseThrow(() -> new MeimaijIIException("查無 " + nameEn + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (!productRepository.findByCategoryId(category.getId()).isEmpty()){
            throw new MeimaijIIException("此種類尚有產品存在，不可刪除", ResponseCode.LOGIC_ISSUE.getCode());
        }
        categoryRepository.delete(category);
    }

    public List<CategoryVo> list(){
        List<CategoryVo> result = new ArrayList<CategoryVo>();
        categoryRepository.findAll().forEach(x -> {
            Rough rough = roughRepository.findById(x.getRoughId()).orElseThrow(() -> new MeimaijIIException("查無 " + x.getRoughId() + " 資料，請系統人員檢查資料庫資料異常", ResponseCode.SYSTEM_ISSUE.getCode()));
            result.add(new CategoryVo(rough.getNameEn(), x.getNameEn(), x.getNameZh()));
        });
        return result;
    }
}
