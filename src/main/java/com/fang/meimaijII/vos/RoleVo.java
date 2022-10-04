package com.fang.meimaijII.vos;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo{

    private Long id;

    @NotNull(message = "角色名稱不可為空")
    @Size(max = 20, message = "角色名稱不可超過20字元")
    private String name;

    private List<Long> moduleIds;

    private Set<ModuleVo> modules;
}
