package com.library.librarymanagementsystem.enums;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * 借阅状态
 */
public enum UserTypeEnum {
    MANAGER("0", "管理员"),
    READER("1", "读者");

    final String key;
    final String value;

    UserTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static UserTypeEnum getKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            for(UserTypeEnum item: UserTypeEnum.values()){
                if(key.equals(item.key)){
                    return item;
                }
            }
        }
        return null;
    }

    public static String getString(String key) {
        if (StringUtils.isNotBlank(key)) {
            for(UserTypeEnum item: UserTypeEnum.values()){
                if(key.equals(item.key)){
                    return item.value;
                }
            }
        }
        return null;
    }
}
