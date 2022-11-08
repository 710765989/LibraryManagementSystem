package com.library.librarymanagementsystem.domian.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdatePwdDto {
    @NotBlank(message = "请输入旧密码")
    private String oldPwd;

    @NotBlank(message = "请输入新密码")
    private String newPwd;

    @NotBlank(message = "请输入重复密码")
    private String repeatPwd;
}
