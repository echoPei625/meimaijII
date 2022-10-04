package com.fang.meimaijII.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
    
    Optional<Brand> findByNameEn(String nameEn);
}
