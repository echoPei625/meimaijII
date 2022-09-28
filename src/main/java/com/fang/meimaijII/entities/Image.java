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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "IMAGE")
public class Image implements Serializable{

    private static final long serialVersionUID = -3260480217304325572L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "local_path", length = 256, nullable = false)
    private String localPath;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "create_time", nullable = false)
    private Date createTime;
}
