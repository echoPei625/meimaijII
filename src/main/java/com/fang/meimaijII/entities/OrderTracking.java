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
@Table(name = "ORDER_TRACKING")
@Data
public class OrderTracking implements Serializable{

    private static final long serialVersionUID = 6462231417070316413L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "creator", nullable = false)
    private Long creator;

    @Column(name = "create_time", nullable = false)
    private Date createTime;
}
