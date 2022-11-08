package com.library.librarymanagementsystem.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePwdDto;
import com.library.librarymanagementsystem.entity.User;

public interface UserService extends IService<User> {
    User getByUsername(String username);

    /**
     * 更新密码
     * @param pwdDto
     */
    void updatePwd(UserUpdatePwdDto pwdDto);
}
