package com.fang.meimaijII.vos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BrandVo{

    @NotBlank(message = "英文名稱不可為空")
    @Size(max = 50,message = "英文名稱不可超過50字元")
    private String nameEn;

    @NotBlank(message = "中文名稱不可為空")
    @Size(max = 50, message = "中文名稱不可超過50字元")
    private String nameZh;

    private ImageVo image;

    private Long imageId;

    @Size(max = 500, message = "描述欄不可超過500字元")
    private String description;
}
