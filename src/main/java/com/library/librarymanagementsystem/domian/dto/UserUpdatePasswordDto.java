package com.library.librarymanagementsystem.domian.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdatePasswordDto {
    @NotBlank(message = "请输入旧密码")
    private String oldPassword;

    @NotBlank(message = "请输入新密码")
    private String newPassword;

    @NotBlank(message = "请输入重复密码")
    private String repeatPassword;
}
