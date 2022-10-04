package com.fang.meimaijII.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.meimaijII.entities.Module;
import com.fang.meimaijII.entities.Role;
import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.repositories.ModuleRepository;
import com.fang.meimaijII.repositories.RoleRepository;
import com.fang.meimaijII.repositories.UserRepository;
import com.fang.meimaijII.utils.MeimaijIIException;
import com.fang.meimaijII.vos.ModuleVo;
import com.fang.meimaijII.vos.RoleVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private UserRepository userRepository;

    public void create(RoleVo vo){
        if (roleRepository.findByName(vo.getName()).isPresent()){
            throw new MeimaijIIException("角色名稱已存在", ResponseCode.LOGIC_ISSUE.getCode());
        }
        Set<Module> modules = new HashSet<Module>(moduleRepository.findByIdIn(vo.getModuleIds()));
        Role role = Role.builder().name(vo.getName()).module(modules).build();
        roleRepository.save(role);
    }

    public void update(Long roleId, RoleVo vo){
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new MeimaijIIException("查無此角色", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (null != vo.getName()){
            if (roleRepository.findByName(vo.getName()).isPresent()){
                throw new MeimaijIIException("角色名稱已存在", ResponseCode.LOGIC_ISSUE.getCode());
            }
            role.setName(vo.getName());
        }
        if (null != vo.getModuleIds()){
            Set<Module> modules = new HashSet<Module>(moduleRepository.findByIdIn(vo.getModuleIds()));
            role.setModule(modules);
        }
        roleRepository.save(role);
    }

    public RoleVo get(Long id){
        Role role = roleRepository.findById(id).orElseThrow(() -> new MeimaijIIException("查無此角色", ResponseCode.DATA_NOT_FOUND.getCode()));
        RoleVo result = new RoleVo();
        Set<ModuleVo> modules = role.getModule().stream().map((x) -> {
            return ModuleVo.builder().id(x.getId()).nameZh(x.getNameZh()).build();
        }).collect(Collectors.toSet());
        result.setName(role.getName());
        result.setModules(modules);
        return result;
    }

    public List<RoleVo> list(){
        List<RoleVo> result = roleRepository.findAll().stream().map((x) -> {
            return RoleVo.builder().id(x.getId()).name(x.getName()).build();
        }).collect(Collectors.toList());
        return result;
    }

    public void delete(Long id){
        Role role = roleRepository.findById(id).orElseThrow(() -> new MeimaijIIException("查無此角色", ResponseCode.DATA_NOT_FOUND.getCode()));
        if (!userRepository.findByRoleId(id).isEmpty()){
            throw new MeimaijIIException("此角色尚有使用者綁定，不可刪除", ResponseCode.LOGIC_ISSUE.getCode());
        }
        roleRepository.delete(role);
    }
}
