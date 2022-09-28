package com.fang.meimaijII.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "MODULE")
@Getter
public class Module implements Serializable{

    private static final long serialVersionUID = -1239159726433250374L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "modules", fetch = FetchType.LAZY)
    private Set<Role> roles;
}
