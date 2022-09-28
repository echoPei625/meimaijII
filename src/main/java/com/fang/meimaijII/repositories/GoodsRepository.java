package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Goods;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long>{
}
