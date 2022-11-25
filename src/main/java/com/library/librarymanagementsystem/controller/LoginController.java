package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.utils.R;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserServiceImpl userService;

    /**
     * 用户登录
     * @param user 用户信息
     */
    @PostMapping("/login")
    public R login(@RequestBody User user, HttpServletResponse response) {
        User userByUsername = userService.getByUsername(user.getUsername());
        if (userByUsername == null) {
            return R.error("用户信息不存在！");
        }
        String password = user.getPassword();
        try {
            String md5 = getMD5(password);
            if (!md5.equals(userByUsername.getPassword())) {
                return R.error("密码错误！");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        ShiroUtils.login(userByUsername);
        return R.ok("登录成功");
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public R logout() {
        ShiroUtils.logout();
        return R.ok();
    }

    /**
     * md5加密
     *
     * @param str 待加密字符串
     * @return 加密后结果
     */
    public static String getMD5(String str) throws NoSuchAlgorithmException {
        return new Md5Hash(str, "salt", 3).toHex();
    }
}
