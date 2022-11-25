package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.service.impl.LoginServiceImpl;
import com.library.librarymanagementsystem.utils.R;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginServiceImpl loginService;

    /**
     * 用户登录
     * @param user 用户信息
     */
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        try {
            String userType = loginService.login(user);
            return R.ok("登录成功").put("userType", userType);
        } catch (NoSuchAlgorithmException | RuntimeException e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public R logout() {
        ShiroUtils.logout();
        return R.ok();
    }
}
