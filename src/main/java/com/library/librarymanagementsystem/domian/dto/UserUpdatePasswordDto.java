package com.library.librarymanagementsystem.domian.dto;

import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
public class UserUpdatePasswordDto {
    @NotBlank(message = "请输入旧密码")
    private String oldPassword;

    @NotBlank(message = "请输入新密码")
    private String newPassword;

    @NotBlank(message = "请输入重复密码")
    private String repeatPassword;

    /**
     * 登录校验
     * @param user 当前登录用户
     */
    public void validate(User user) {
        if (!Objects.equals(ShiroUtils.getMD5(oldPassword), user.getPassword())) {
            throw new RuntimeException("旧密码错误");
        }
        if (oldPassword.equals(newPassword)){
            throw new RuntimeException("新旧密码不能相同");
        }
        if (!repeatPassword.equals(newPassword)){
            throw new RuntimeException("新密码与确认密码不一致");
        }
    }
}
