package com.fang.meimaijII.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    List<Category> findByRoughId(Long roughId);
    
    Optional<Category> findByNameEn(String nameEn);
}
