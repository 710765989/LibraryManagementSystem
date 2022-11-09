package com.library.librarymanagementsystem.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.library.librarymanagementsystem.domian.dto.UserCreateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePwdDto;
import com.library.librarymanagementsystem.domian.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    User getByUsername(String username);

    /**
     * 更新密码
     * @param pwdDto
     */
    void updatePwd(UserUpdatePwdDto pwdDto);

    /**
     * 获取用户列表
     */
    List<User> getUserList();

    /**
     * @description: 新增用户
     * @params: [createDto]
     * @return: void
     * @author: hxl
     * @date: 2022/11/9 14:58
     */
    void addUser(UserCreateDto createDto);

    /**
     * @description: 编辑用户
     * @params: [updateDto]
     * @return: void
     * @author: hxl
     * @date: 2022/11/9 15:04
     */
    void updateUser(UserUpdateDto updateDto);
}
