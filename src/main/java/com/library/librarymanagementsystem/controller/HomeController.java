package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.utils.R;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/info")
    public R info (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String authType = request.getAuthType();
        String token = request.getHeader("x-token");
        System.out.println(token);
        System.out.println(ShiroUtils.getSubject().isAuthenticated());
        if (ShiroUtils.getCurrentUser() == null) {
            //return R.error("请先登录！");
        }
        Map<String, String> data = new HashMap<>();
        if (ShiroUtils.getCurrentUser() != null) {
            data.put("name", ShiroUtils.getCurrentUser().getUsername());
            System.out.println(ShiroUtils.getCurrentUser().getUsername());
        } else {
            User admin = userService.getByUsername("admin");
            data.put("name", admin.getUsername());

        }
        //data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("avatar", "https://avatars.githubusercontent.com/u/22955417?s=400&u=48e3807db8543a1b00d71938799d13cf27b27e30&v=4");
        return R.ok().put("data", data);
    }
}
