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
@Table(name = "BRAND")
@Data
public class Brand implements Serializable{

    private static final long serialVersionUID = -384216662749296723L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_en", nullable = false, length = 50, unique = true)
    private String nameEn;
    
    @Column(name = "name_zh", nullable = false, length = 50)
    private String nameZh;

    @Column(name = "image_id", nullable = true)
    private Long imageId;

    @Column(name = "description", nullable = true, length = 500)
    private String description;
}
