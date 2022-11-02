package com.library.librarymanagementsystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    /**
     * id
     */
    //@NotNull(message="[id]不能为空")
    private Integer id;

    /**
     * 创建时间
     */
    //@NotNull(message="[创建时间]不能为空")
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
