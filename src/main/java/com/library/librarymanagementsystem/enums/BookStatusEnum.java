package com.library.librarymanagementsystem.enums;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * 借阅状态
 */
public enum BookStatusEnum {
    IN_STOCK("0", "在库"),
    BORROWED("1", "借出");

    final String value;
    final String status;

    BookStatusEnum(String value, String status) {
        this.value = value;
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public String getStatus() {
        return status;
    }

    public static BookStatusEnum getBookStatus(String value) {
        if (StringUtils.isNotBlank(value)) {
            for(BookStatusEnum item: BookStatusEnum.values()){
                if(value.equals(item.value)){
                    return item;
                }
            }
        }
        return null;
    }

    public static String getStatus(String value) {
        if (StringUtils.isNotBlank(value)) {
            for(BookStatusEnum item: BookStatusEnum.values()){
                if(value.equals(item.value)){
                    return item.status;
                }
            }
        }
        return null;
    }
}
