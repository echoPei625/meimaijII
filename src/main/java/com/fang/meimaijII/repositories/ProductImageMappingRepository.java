package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.ProductImageMapping;

@Repository
public interface ProductImageMappingRepository extends JpaRepository<ProductImageMapping, Long>{
}
