package com.library.librarymanagementsystem.domian.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* 延期归还
* @TableName delay
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class Delay extends BaseEntity {
    /**
    * 用户id
    */
    @NotNull(message="[用户id]不能为空")
    private Integer userId;
    /**
    * 图书id
    */
    @NotNull(message="[图书id]不能为空")
    private Integer bookId;
    /**
    * 借阅id
    */
    @NotNull(message="[借阅id]不能为空")
    private Integer borrowId;
    /**
    * 延期天数
    */
    @NotBlank(message="[延期天数]不能为空")
    //@Size(max= 50,message="延期天数长度不能超过50")
    //@Length(max= 50,message="延期天数长度不能超过50")
    private Integer time;
}
