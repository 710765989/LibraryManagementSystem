package com.library.librarymanagementsystem.service.impl;

import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.service.LoginService;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService  {
    private final UserServiceImpl userService;

    /**
     * 用户登录
     * @param user 登录信息
     */
    @Override
    public String login(User user) throws NoSuchAlgorithmException {
        // 用户存在判断
        User userByUsername = userService.getByUsername(user.getUsername());
        if (userByUsername == null) {
            throw new RuntimeException("用户信息不存在！");
        }
        String password = user.getPassword();
        // 密码转md5
        String md5 = ShiroUtils.getMD5(password);
        // 密码判断
        if (!md5.equals(userByUsername.getPassword())) {
            throw new RuntimeException("密码错误！");
        }
        // 用户登录
        ShiroUtils.login(userByUsername);
        return Objects.nonNull(ShiroUtils.getCurrentManager()) ? ShiroUtils.CURRENT_MANAGER : ShiroUtils.CURRENT_READER;
    }
}
