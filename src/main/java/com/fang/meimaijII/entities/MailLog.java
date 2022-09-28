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
@Table(name = "MAIL_LOG")
@Data
public class MailLog implements Serializable{

    private static final long serialVersionUID = 2281763854905574721L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pipe", nullable = false, length = 30)
    private String pipe;

    @Column(name = "reason_type", nullable = false)
    private Integer reasonType;

    @Column(name = "reason", nullable = false, length = 20)
    private String reason;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "recipients_excel", nullable = true)
    private String recipientsExcel;
}
