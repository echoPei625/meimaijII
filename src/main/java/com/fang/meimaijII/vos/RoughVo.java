package com.fang.meimaijII.vos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoughVo{

    @NotBlank(message = "nameEn 不可為空")
    @Size(max = 30, message = "nameEn 不可超過30字元")
    private String nameEn;

    @NotBlank(message = "nameZh 不可為空")
    @Size(max = 10, message = "nameZh 不可超過10字元")
    private String nameZh;
}
