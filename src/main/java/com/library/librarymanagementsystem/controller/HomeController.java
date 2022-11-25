package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.utils.R;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    /**
     * 登录首页
     */
    @GetMapping("/info")
    public R info () {
        if (ShiroUtils.getCurrentUser() == null) {
            return R.error("请先登录！");
        }
        Map<String, String> data = new HashMap<>();
        // 用户名
        data.put("name", ShiroUtils.getCurrentUser().getUsername());
        // 头像
        data.put("avatar", "https://avatars.githubusercontent.com/u/22955417?s=400&u=48e3807db8543a1b00d71938799d13cf27b27e30&v=4");
        return R.ok().put("data", data);
    }
}
