package com.fang.meimaijII.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ITEM")
public class Item implements Serializable{

    private static final long serialVersionUID = 6437278537972298862L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", nullable = false, length = 50, unique = true)
    private String uid;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "option1", nullable = true, length = 20)
    private String option1;

    @Column(name = "option2", nullable = true, length = 20)
    private String option2;

    @Column(name = "item_prefix", nullable = false, length = 10)
    private String itemPrefix;

    @Column(name = "goods_amount", nullable = false)
    private Integer goodsAmount;

    @Column(name = "occupied_amount", nullable = true)
    private Integer occupiedAmount;

    @Column(name = "warn_amount", nullable = true)
    private Integer warnAmount;

    @Column(name = "comment_amount", nullable = false)
    private Integer commentAmount;

    @Column(name = "comment_stars_amount", nullable = false)
    private Integer commentStarsAmount;

    @Column(name = "price_show", nullable = false)
    private Integer priceShow;

    @Column(name = "price_real", nullable = false)
    private Integer priceReal;

    @Column(name = "average_cost", nullable = true)
    private Double averageCost;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;
}
