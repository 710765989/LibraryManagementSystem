package com.library.librarymanagementsystem.domian.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
* 书籍
* @TableName book
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class Dictionary extends BaseEntity {
    /**
    * 词典key
    */
    @NotBlank(message="[词典key]不能为空")
    @Size(max= 255,message="词典key长度不能超过255")
    @Length(max= 255,message="词典key长度不能超过255")
    private String dicKey;
    /**
    * 词典内容值
    */
    @NotBlank(message="[词典内容值]不能为空")
    @Size(max= 255,message="词典内容值长度不能超过1")
    @Length(max= 255,message="词典内容值长度不能超过1")
    private String value;
    /**
    * 词典内容文本
    */
    @NotBlank(message="[词典内容文本]不能为空")
    @Size(max= 1,message="词典内容文本长度不能超过255")
    @Length(max= 1,message="词典内容文本长度不能超过255")
    private String text;
    /**
    * 使用状态
    * 0: 未使用 1: 使用中
    */
    @NotBlank(message="[使用状态]不能为空")
    @Size(max= 1,message="使用状态长度不能超过1")
    @Length(max= 1,message="使用状态长度不能超过1")
    private String useFlag;
}
