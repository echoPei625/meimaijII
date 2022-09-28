package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.CouponUserMapping;

@Repository
public interface CouponUserMappingRepository extends JpaRepository<CouponUserMapping, Long>{
}
