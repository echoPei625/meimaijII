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
@Table(name = "PRODUCT")
@Data
public class Product implements Serializable{

    private static final long serialVersionUID = -8689793578604932634L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", nullable = false, length = 50, unique = true)
    private String uid;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "product_prefix", nullable = false, length = 5)
    private String productPrefix;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "recommended_rate", nullable = true)
    private Integer recommendedRate;

    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Column(name = "content", nullable = true, length = 5000)
    private String content;

    @Column(name = "main_image_id", nullable = true)
    private Long mainImageId;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @Column(name = "classify_by1", length = 10, nullable = true)
    private String classifyBy1;

    @Column(name = "classify_by2", length = 10, nullable = true)
    private String classifyBy2;
}
