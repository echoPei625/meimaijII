package com.fang.meimaijII.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CATEGORY")
@Data
public class Category implements Serializable{

    private static final long serialVersionUID = -6637702559652532123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_en", nullable = false, unique = true, length = 30)
    private String nameEn;

    @Column(name = "name_zh", nullable = false, length = 30)
    private String nameZh;
}
