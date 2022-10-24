package com.fang.meimaijII.vos;

import lombok.Data;

@Data
public class ItemVo{

    private String[] options;

    private Integer warnAmount;

    private Integer priceShow;

    private Integer priceReal;

    private Boolean isPublic;

    private String itemPrefix;
}
