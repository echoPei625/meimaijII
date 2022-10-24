package com.fang.meimaijII.vos;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserVo{

    @NotNull(message = "帳戶不可為空")
    @Size(max = 20, message = "帳戶不可超過20字元")
    private String account;

    @NotBlank(message = "密碼不可為空")
    @Size(max = 20, message = "密碼不可超過20字元")
    private String password;

    @NotBlank(message = "姓氏不可為空")
    @Size(max = 3, message = "姓氏不可超過3個字元")
    private String lastName;

    @NotBlank(message = "名字不可為空")
    @Size(max = 10, message = "名字不可超過10個字元")
    private String firstName;

    private Long imageId;

    @NotNull(message = "roleId不可為空")
    private Long roleId;

    @Size(max = 256, message = "地址不可超過256字元")
    private String address;

    private Boolean gender;

    @NotBlank(message = "email不可唯空")
    @Size(max = 50, message = "email不可超過50字元")
    @Email(message = "email格式有誤")
    private String email;

    @NotNull(message = "mailNews 不可為空")
    private Boolean mailNews;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "生日不可為空")
    private Date birth;
}
