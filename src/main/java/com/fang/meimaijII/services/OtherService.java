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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fang.meimaijII.entities.Image;
import com.fang.meimaijII.entities.User;
import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.repositories.ImageRepository;
import com.fang.meimaijII.repositories.ModuleRepository;
import com.fang.meimaijII.repositories.UserRepository;
import com.fang.meimaijII.utils.FileLimitUtil;
import com.fang.meimaijII.utils.JwtUtils;
import com.fang.meimaijII.utils.MeimaijIIException;
import com.fang.meimaijII.vos.ImageVo;
import com.fang.meimaijII.vos.LoginVo;
import com.fang.meimaijII.vos.ModuleVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class OtherService{

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

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
                throw new MeimaijIIException("不可上傳空的檔案", ResponseCode.INPUT_ISSUE.getCode());
            }else if (file.getSize() > FileLimitUtil.getMaxSize()){
                throw new MeimaijIIException("上傳檔案不可超過2MB", ResponseCode.INPUT_ISSUE.getCode());
            }else if (fileName.contains("..")){
                throw new MeimaijIIException("副檔名不可包含 ..", ResponseCode.INPUT_ISSUE.getCode());
            }
            fileName = UUID.randomUUID().toString() + "." + getExt(fileName);
            Files.createDirectories(rootPath);
            try(InputStream inputStream = file.getInputStream()){
                Path target = rootPath.resolve(fileName);
                Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch(IOException e){
            throw new MeimaijIIException("檔案上傳失敗" + fileName, e, ResponseCode.SYSTEM_ISSUE.getCode());
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

    public boolean deleteImage(Image image){
        Path deletePath = Paths.get(rootPath.toString(), image.getName());
        try{
            Files.deleteIfExists(deletePath);
        }catch(Exception e){
            throw new MeimaijIIException("無法刪除圖片" + e.getMessage(), ResponseCode.SYSTEM_ISSUE.getCode());
        }
        imageRepository.delete(image);
        return true;
    }

    public List<ModuleVo> getModules(){
        List<ModuleVo> result = moduleRepository.findAll().stream().map(x -> {
            ModuleVo vo = ModuleVo.builder().id(x.getId()).nameZh(x.getNameZh()).build();
            return vo;
        }).collect(Collectors.toList());
        return result;
    }

    public Map<String, Object> login(LoginVo vo){
        User user = userRepository.findByAccount(vo.getAccount()).orElseThrow(() -> new MeimaijIIException("查無此帳號", ResponseCode.DATA_NOT_FOUND.getCode()));
        boolean pass = bCryptPasswordEncoder.matches(vo.getPassword(), user.getPassword());
        if (!pass){
            throw new MeimaijIIException("密碼不符", ResponseCode.LOGIC_ISSUE.getCode());
        }
        Map<String, Object> map = jwtUtils.generate(user, new Date());
        user.setRefreshToken(map.get("refreshToken").toString());
        userRepository.save(user);
        return map;
    }
    
    public Map<String, Object> verify(HttpServletRequest request) {
        Map<String, Object> info = jwtUtils.verifyToken(request);
        return info;
    }
    
    public Map<String, Object> refresh(String refreshToken) {
        User user = userRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new MeimaijIIException("查無此組token", ResponseCode.DATA_NOT_FOUND.getCode()));
        Map<String, Object> result = jwtUtils.generate(user, new Date());
        user.setRefreshToken(result.get("refreshToken").toString());
        userRepository.save(user);
        return result;
    }
}
