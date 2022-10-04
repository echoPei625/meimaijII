package com.fang.meimaijII.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.meimaijII.entities.Rough;
import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.repositories.CategoryRepository;
import com.fang.meimaijII.repositories.RoughRepository;
import com.fang.meimaijII.utils.MeimaijIIException;
import com.fang.meimaijII.vos.CategoryVo;
import com.fang.meimaijII.vos.RoughVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoughService{

    @Autowired
    private RoughRepository roughRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(RoughVo vo){
        if (roughRepository.findByNameEn(vo.getNameEn()).isPresent()){
            throw new MeimaijIIException("粗類英文名稱已存在", ResponseCode.LOGIC_ISSUE.getCode());
        }
        Rough rough = Rough.builder().nameEn(vo.getNameEn()).nameZh(vo.getNameZh()).build();
        roughRepository.save(rough);
    }

    public void update(String roughEn, RoughVo vo){
        Rough rough = roughRepository.findByNameEn(roughEn).orElseThrow(() -> new MeimaijIIException("查無 " + roughEn + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (null != vo.getNameEn()){
            if (roughRepository.findByNameEn(vo.getNameEn()).isPresent()){
                throw new MeimaijIIException("粗類英文名稱已存在", ResponseCode.LOGIC_ISSUE.getCode());
            }else{
                rough.setNameEn(vo.getNameEn());
            }
        }
        if (null != vo.getNameZh()){
            rough.setNameZh(vo.getNameZh());
        }
        roughRepository.save(rough);
    }

    public List<RoughVo> list(){
        List<RoughVo> result = new ArrayList<RoughVo>();
        roughRepository.findAll().forEach(x -> {
            result.add(new RoughVo(x.getNameEn(), x.getNameZh()));
        });
        return result;
    }

    public void delete(String nameEn){
        Rough rough = roughRepository.findByNameEn(nameEn).orElseThrow(() -> new MeimaijIIException("查無 " + nameEn + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (!categoryRepository.findByRoughId(rough.getId()).isEmpty()){
            throw new MeimaijIIException("此粗類尚有細類存在，不可刪除", ResponseCode.LOGIC_ISSUE.getCode());
        }
        roughRepository.delete(rough);
    }

    public List<CategoryVo> getCategories(String nameEn){
        Rough rough = roughRepository.findByNameEn(nameEn).orElseThrow(() -> new MeimaijIIException("查無 " + nameEn + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
        List<CategoryVo> result = categoryRepository.findByRoughId(rough.getId()).stream().map((x) -> {
            CategoryVo vo = new CategoryVo();
            vo.setCategoryNameEn(x.getNameEn());
            vo.setCategoryNameZh(x.getNameZh());
            return vo;
        }).collect(Collectors.toList());
        return result;
    }
}
