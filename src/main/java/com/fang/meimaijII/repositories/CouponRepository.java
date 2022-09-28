package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{
}
