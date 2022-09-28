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
@Table(name = "COUPON")
@Data
public class Coupon implements Serializable{

    private static final long serialVersionUID = -1946866683026083274L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "code", nullable = false, length = 10, unique = true)
    private String code;

    @Column(name = "receive_start", nullable = true)
    private Date receiveStart;

    @Column(name = "receive_end", nullable = true)
    private Date receiveEnd;

    @Column(name = "use_start", nullable = false)
    private Date useStart;

    @Column(name = "use_end", nullable = false)
    private Date useEnd;

    @Column(name = "max_per_person", nullable = true)
    private Integer maxPerPerson;

    @Column(name = "denominator", nullable = true)
    private Integer denominator;

    @Column(name = "numerator", nullable = true)
    private Integer numerator;

    @Column(name = "need_receive", nullable = false)
    private Boolean needReceive;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "min_price", nullable = false)
    private Integer minPrice;

    @Column(name = "discount", nullable = true)
    private Integer discount;
}
