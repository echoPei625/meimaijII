package com.fang.meimaijII.vos;

import java.util.List;

import lombok.Data;

@Data
public class ItemCreateVo{

    private String productUid;

    private String[] classifyBy;

    private List<ItemVo> items;
}
