package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.ItemPriceLog;

@Repository
public interface ItemPriceLogRepository extends JpaRepository<ItemPriceLog, Long>{
}
