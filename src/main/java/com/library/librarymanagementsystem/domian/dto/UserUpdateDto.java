package com.library.librarymanagementsystem.domian.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateDto extends UserCreateDto{
    /**
     * id
     */
    @NotNull(message = "用户ID不能为空")
    private Integer id;
}
