package com.fang.meimaijII.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fang.meimaijII.entities.Image;
import com.fang.meimaijII.repositories.ImageRepository;
import com.fang.meimaijII.utils.FileLimitUtil;
import com.fang.meimaijII.utils.MeimaijIIException;
import com.fang.meimaijII.vos.ImageVo;

@Service
public class OtherService{

    @Autowired
    private ImageRepository imageRepository;

    private final Path rootPath = Paths.get(FileLimitUtil.getRootPath());

    public List<ImageVo> upload(MultipartFile[] files){
        Date now = new Date();
        List<Image> images = new ArrayList<Image>();
        for(MultipartFile file : files){
            // 不檢查副檔名
            String fileName = storeFile(file);
            Image imageDb = Image.builder().createTime(now).localPath(FileLimitUtil.getDbPath() + fileName).size(file.getSize()).name(fileName).build();
            images.add(imageDb);
        }
        imageRepository.saveAll(images);
        List<ImageVo> result = images.stream().map((x) -> {
            ImageVo vo = new ImageVo(x.getId(), x.getLocalPath());
            return vo;
        }).collect(Collectors.toList());
        return result;
    }

    private String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if (file.isEmpty()){
                throw new MeimaijIIException("不可上傳空的檔案");
            }else if (file.getSize() > FileLimitUtil.getMaxSize()){
                throw new MeimaijIIException("上傳檔案不可超過2MB");
            }else if (fileName.contains("..")){
                throw new MeimaijIIException("副檔名不可包含 ..");
            }
            fileName = UUID.randomUUID().toString() + "." + getExt(fileName);
            Files.createDirectories(rootPath);
            try(InputStream inputStream = file.getInputStream()){
                Path target = rootPath.resolve(fileName);
                Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch(IOException e){
            throw new MeimaijIIException("檔案上傳失敗" + fileName, e);
        }
        return fileName;
    }

    private String getExt(String fileName){
        if (fileName != null && fileName.lastIndexOf(".") >= 0){
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }else{
            return "";
        }
    }
}
