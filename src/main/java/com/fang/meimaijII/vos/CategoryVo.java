package com.fang.meimaijII.vos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CategoryVo{

    @NotBlank(message = "roughNameEn 不可為空")
    private String roughNameEn;

    @NotBlank(message = "categoryNameEn 不可為空")
    @Size(max = 30, message = "categoryNameEn 不可超過30字元")
    private String categoryNameEn;

    @NotBlank(message = "categoryNameZh 不可為空")
    @Size(max = 10, message = "categoryNameZh 不可超過10字元")
    private String categoryNameZh;
}
