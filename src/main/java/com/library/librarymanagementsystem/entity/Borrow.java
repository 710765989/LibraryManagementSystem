package com.library.librarymanagementsystem.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
* 借阅
* @TableName borrow
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class Borrow extends BaseEntity {
    /**
    * 用户id
    */
    //@NotNull(message="[用户id]不能为空")
    private Integer userId;
    /**
    * 图书id
    */
    @NotNull(message="[图书id]不能为空")
    private Integer bookId;
    /**
    * 归还状态
    * 0:已归还 1:未归还
    */
    //@NotBlank(message="[0:已归还 1:未归还]不能为空")
    @Size(max= 1,message="归还状态长度不能超过1")
    @Length(max= 1,message="归还状态长度不能超过1")
    private String status;
    /**
    * 预计归还时间
    */
    @NotNull(message = "归还时间不能为空")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnTime;
    /**
    * 实际归还时间
    */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime realReturnTime;
}
