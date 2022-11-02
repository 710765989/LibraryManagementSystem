package com.library.librarymanagementsystem.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
* 书籍
* @TableName book
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class Book extends BaseEntity {
    /**
    * 书名
    */
    @NotBlank(message="[书名]不能为空")
    @Size(max= 255,message="书名长度不能超过255")
    @Length(max= 255,message="书名长度不能超过255")
    private String name;
    /**
    * 作者
    */
    @NotBlank(message="[作者]不能为空")
    @Size(max= 255,message="作者长度不能超过255")
    @Length(max= 255,message="作者长度不能超过255")
    private String author;
    /**
    * 状态
    */
    @NotBlank(message="[状态]不能为空")
    @Size(max= 1,message="状态长度不能超过1")
    @Length(max= 1,message="状态长度不能超过1")
    private String status;
    /**
    * 借阅id
    */
    private Integer borrowId;
    /**
    * 图书类型
    */
    @Size(max= 1,message="图书类型长度不能超过1")
    @Length(max= 1,message="图书类型长度不能超过1")
    private String type;
}
