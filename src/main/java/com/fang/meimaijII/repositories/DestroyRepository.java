package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Destroy;

@Repository
public interface DestroyRepository extends JpaRepository<Destroy, Long>{
}
