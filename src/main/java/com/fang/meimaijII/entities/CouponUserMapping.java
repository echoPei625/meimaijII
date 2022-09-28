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
@Table(name = "COUPON_USER_MAPPING")
@Data
public class CouponUserMapping implements Serializable{

    private static final long serialVersionUID = 331310735248743049L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "coupon_id", nullable = false)
    private Long couponId;

    @Column(name = "receive_amount", nullable = true)
    private Integer receivedAmount;

    @Column(name = "used_amount", nullable = false)
    private Integer usedAmount;
}
