package com.fang.meimaijII.vos;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginVo{

    @NotNull(message = "帳號不可為空")
    private String account;

    @NotNull(message = "密碼不可為空")
    private String password;
}
