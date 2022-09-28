package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
