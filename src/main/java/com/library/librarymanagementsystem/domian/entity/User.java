package com.library.librarymanagementsystem.domian.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
* 用户
* @TableName user
*/
@TableName("user")
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {
    /**
    * 用户名
    */
    @NotBlank(message="[用户名]不能为空")
    @Size(max= 255,message="用户名长度不能超过255")
    @Length(max= 255,message="用户名长度不能超过255")
    private String username;
    /**
    * 密码
    */
    @NotBlank(message="[密码]不能为空")
    @Size(max= 255,message="密码长度不能超过255")
    @Length(max= 255,message="密码长度不能超过255")
    private String password;
    /**
    * 用户身份类型
    */
    //@NotBlank(message="[用户身份类型]不能为空")
    @Size(max= 1,message="编码长度不能超过1")
    @Length(max= 1,message="编码长度不能超过1")
    private String type;
}
