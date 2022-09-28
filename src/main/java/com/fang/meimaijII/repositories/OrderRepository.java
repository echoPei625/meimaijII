package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
}
