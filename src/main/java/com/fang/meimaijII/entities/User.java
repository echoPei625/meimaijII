package com.fang.meimaijII.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

    private static final long serialVersionUID = -3464813936657868369L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, length = 50, unique = true)
    private String uuid;

    @Column(name = "image_id", nullable = true)
    private Long imageId;

    @Column(name = "account", nullable = false, length = 20)
    private String account;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "last_name", nullable = false, length = 3)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 10)
    private String firstName;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "address", nullable = true, length = 256)
    private String address;

    @Column(name = "gender", nullable = true)
    private Boolean gender;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Column(name = "mail_news", nullable = false)
    private Boolean mailNews;
    
    @Column(name = "birth", nullable = false)
    private Date birth;
    
    @Column(name = "refresh_token", nullable = false, length = 512)
    private String refreshToken;
}
