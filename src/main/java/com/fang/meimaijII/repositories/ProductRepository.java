package com.fang.meimaijII.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    List<Product> findByCategoryId(Long categoryId);
    
    List<Product> findByBrandId(Long brandId);
    
    Optional<Product> findByUid(String uid);
}
