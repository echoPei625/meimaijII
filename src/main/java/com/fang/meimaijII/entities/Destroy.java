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
@Table(name = "DESTROY")
@Data
public class Destroy implements Serializable{

    private static final long serialVersionUID = 6276222298641887397L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goods_id", nullable = false)
    private Long goodsId;

    @Column(name = "reason", nullable = false, length = 10)
    private String reason;

    @Column(name = "description", nullable = true, length = 100)
    private String description;

    @Column(name = "create_time", nullable = false)
    private Date createTime;
}
