package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.OrderTracking;

@Repository
public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long>{
}
