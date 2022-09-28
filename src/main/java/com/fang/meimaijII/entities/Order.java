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
@Table(name = "ORDER")
@Data
public class Order implements Serializable{

    private static final long serialVersionUID = -854801376372571876L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", nullable = false, unique = true, length = 20)
    private String uid;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "paid_method", nullable = false)
    private Integer paidMethod;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @Column(name = "address", nullable = false, length = 256)
    private String address;

    @Column(name = "recipient", nullable = false, length = 20)
    private String recipient;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Column(name = "commented", nullable = true)
    private Boolean commented;

    @Column(name = "coupon_id", nullable = true)
    private Long couponId;

    @Column(name = "delivery_fee", nullable = false)
    private Integer deliveryFee;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "actual_get", nullable = true)
    private Integer actualGet;
}
