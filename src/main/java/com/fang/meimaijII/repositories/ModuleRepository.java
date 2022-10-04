package com.fang.meimaijII.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{
    
    List<Module> findByIdIn(List<Long> ids);
}
