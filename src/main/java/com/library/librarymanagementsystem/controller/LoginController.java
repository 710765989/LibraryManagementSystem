package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.utils.R;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@RestController
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

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
        //try {
            //String md5 = getMD5(password);
            //if (!md5.equals(userByUsername.getPassword())) {
            if (!password.equals(userByUsername.getPassword())) {
                return R.error("密码错误！");
            }
        //} catch (NoSuchAlgorithmException e) {
        //    e.printStackTrace();
        //}
        System.out.println(ShiroUtils.getSubject().isAuthenticated());
        Serializable sessionId = ShiroUtils.login(userByUsername);
        System.out.println(ShiroUtils.getSubject().isAuthenticated());

        Cookie cookie = new Cookie("token", sessionId.toString());
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        response.addHeader("token", sessionId.toString());
        //ShiroUtils.getSession().setAttribute("token", USER_TYPE_MANAGER.equals(userByUsername.getType()) ? ADMIN_TOKEN : USER_TOKEN);
        return R.ok("登录成功")
                .put("token", sessionId);
                //.put("token", USER_TYPE_MANAGER.equals(userByUsername.getType()) ? ADMIN_TOKEN : USER_TOKEN);
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
        String salt = "salt";
        //MessageDigest md = MessageDigest.getInstance("MD5");
        //md.update((str+salt).getBytes());
        //return new BigInteger(1, md.digest()).toString(16);

        int count = 3;
        Md5Hash md5Hash = new Md5Hash(str, salt, count);
        return md5Hash.toHex();
    }
}
