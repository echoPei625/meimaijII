package com.fang.meimaijII.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ITEM_PRICE_LOG")
@Data
public class ItemPriceLog implements Serializable{

    private static final long serialVersionUID = -1766981054620771820L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "before_price", nullable = false)
    private Integer beforePrice;

    @Column(name = "after_price", nullable = false)
    private Integer afterPrice;

    @Column(name = "reason", nullable = false, length = 30)
    private String reason;

    @Column(name = "creator", nullable = false)
    private Long creator;

    @Column(name = "create_time", nullable = false)
    private Date createTime;
}
