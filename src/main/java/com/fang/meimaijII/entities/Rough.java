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
@Table(name = "ROUGH")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rough implements Serializable{

    private static final long serialVersionUID = -5989031208767104843L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_en", nullable = false, length = 30, unique = true)
    private String nameEn;

    @Column(name = "name_zh", nullable = false, length = 30)
    private String nameZh;
}
