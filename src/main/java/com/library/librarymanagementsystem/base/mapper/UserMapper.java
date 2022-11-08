package com.library.librarymanagementsystem.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.librarymanagementsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username}")
    User getByUsername (String username);
}
