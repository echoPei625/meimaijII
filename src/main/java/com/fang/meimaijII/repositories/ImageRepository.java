package com.fang.meimaijII.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

    List<Image> findByIdIn(List<Long> ids);
}
