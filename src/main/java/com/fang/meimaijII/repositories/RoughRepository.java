package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Rough;

@Repository
public interface RoughRepository extends JpaRepository<Rough, Long>{
}
