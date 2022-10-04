package com.fang.meimaijII.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.meimaijII.entities.Brand;
import com.fang.meimaijII.entities.Image;
import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.repositories.BrandRepository;
import com.fang.meimaijII.repositories.ImageRepository;
import com.fang.meimaijII.repositories.ProductRepository;
import com.fang.meimaijII.utils.MeimaijIIException;
import com.fang.meimaijII.vos.BrandVo;
import com.fang.meimaijII.vos.ImageVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class BrandService{

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    public void create(BrandVo vo){
        if (brandRepository.findByNameEn(vo.getNameEn()).isPresent()){
            throw new MeimaijIIException("品牌英文名稱已存在", ResponseCode.LOGIC_ISSUE.getCode());
        }
        if (imageRepository.findById(vo.getImageId()).isEmpty()){
            throw new MeimaijIIException("查無 " + vo.getImageId() + " 圖片資料", ResponseCode.DATA_NOT_FOUND.getCode());
        }
        Brand brand = Brand.builder().nameEn(vo.getNameEn()).nameZh(vo.getNameZh()).description(vo.getDescription()).imageId(vo.getImageId()).build();
        brandRepository.save(brand);
    }

    public void update(String nameEn, BrandVo vo){
        Brand brand = brandRepository.findByNameEn(nameEn).orElseThrow(() -> new MeimaijIIException("查無 " + nameEn + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (null != vo.getNameEn()){
            if (brandRepository.findByNameEn(vo.getNameEn()).isPresent()){
                throw new MeimaijIIException("品牌英文名稱已存在", ResponseCode.LOGIC_ISSUE.getCode());
            }else{
                brand.setNameEn(vo.getNameEn());
            }
        }
        if (null != vo.getNameZh()){
            brand.setNameZh(vo.getNameZh());
        }
        if (null != vo.getImageId()){
            if (imageRepository.findById(vo.getImageId()).isEmpty()){
                throw new MeimaijIIException("查無 " + vo.getImageId() + " 圖片資料", ResponseCode.DATA_NOT_FOUND.getCode());
            }else{
                brand.setImageId(vo.getImageId());
            }
        }
        if (null != vo.getDescription()){
            brand.setDescription(vo.getDescription());
        }
        brandRepository.save(brand);
    }

    public void delete(String nameEn){
        Brand brand = brandRepository.findByNameEn(nameEn).orElseThrow(() -> new MeimaijIIException("查無 " + nameEn + " 資料", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (!productRepository.findByBrandId(brand.getId()).isEmpty()){
            throw new MeimaijIIException("此品牌上有產品存在，不可刪除", ResponseCode.LOGIC_ISSUE.getCode());
        }
        brandRepository.delete(brand);
    }

    public List<BrandVo> list(){
        List<BrandVo> result = brandRepository.findAll().stream().map((x) -> {
            Image image = imageRepository.findById(x.getImageId()).orElseThrow(() -> new MeimaijIIException("查無 " + x.getImageId() + " 圖片資料，請檢查資料庫異常", ResponseCode.SYSTEM_ISSUE.getCode()));
            ImageVo imageVo = new ImageVo(image.getId(), image.getLocalPath());
            BrandVo vo = BrandVo.builder().nameEn(x.getNameEn()).nameZh(x.getNameZh()).description(x.getDescription()).image(imageVo).build();
            return vo;
        }).collect(Collectors.toList());
        return result;
    }
}
