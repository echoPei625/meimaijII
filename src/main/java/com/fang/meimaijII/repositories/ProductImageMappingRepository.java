package com.fang.meimaijII.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.ProductImageMapping;

@Repository
public interface ProductImageMappingRepository extends JpaRepository<ProductImageMapping, Long>{

    List<ProductImageMapping> findByProductId(Long productId);
}
