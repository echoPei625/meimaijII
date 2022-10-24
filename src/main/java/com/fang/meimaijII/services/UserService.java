package com.fang.meimaijII.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.meimaijII.entities.User;
import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.repositories.UserRepository;
import com.fang.meimaijII.utils.MeimaijIIException;
import com.fang.meimaijII.vos.UserVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void create(UserVo vo){
        if (userRepository.findByAccount(vo.getAccount()).isPresent()){
            throw new MeimaijIIException("此帳號已有人使用", ResponseCode.LOGIC_ISSUE.getCode());
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String pass = bCryptPasswordEncoder.encode(vo.getPassword());
        User user = User.builder().uuid(uuid).account(vo.getAccount()).password(pass).imageId(vo.getImageId()).lastName(vo.getLastName()).firstName(vo.getFirstName()).roleId(vo.getRoleId()).address(vo.getAddress()).gender(vo.getGender()).email(vo.getEmail()).mailNews(vo.getMailNews()).birth(vo.getBirth()).build();
        userRepository.save(user);
    }

    public void update(){
    }

    public void get(){
    }

    public void list(){
    }

    public void delete(){
    }
}
