package com.library.librarymanagementsystem.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.library.librarymanagementsystem.domian.dto.UserCreateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePasswordDto;
import com.library.librarymanagementsystem.domian.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    User getByUsername(String username);

    /**
     * 更新密码
     * @param dto
     */
    void updatePassword(UserUpdatePasswordDto dto);

    /**
     * 获取用户列表
     */
    List<User> getUserList();

    /**
     * 新增用户
     * @param createDto
     */
    void addUser(UserCreateDto createDto);

    /**
     * 编辑用户
     * @param updateDto
     */
    void updateUser(UserUpdateDto updateDto);
}
