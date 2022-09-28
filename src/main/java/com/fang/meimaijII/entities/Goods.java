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
@Table(name = "GOODS")
@Data
public class Goods implements Serializable{

    private static final long serialVersionUID = 3445471746421965539L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "goods_code", nullable = false, unique = true)
    private String goodsCode;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "expire_time", nullable = false)
    private Date expireTime;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "order_id", nullable = false)
    private Long orderId;
}
