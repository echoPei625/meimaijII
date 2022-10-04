package com.fang.meimaijII.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT_IMAGE_MAPPING")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageMapping implements Serializable{

    private static final long serialVersionUID = 6251046355671574932L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    // future maybe add item image
    @Column(name = "image_id", nullable = false)
    private Long imageId;
}
