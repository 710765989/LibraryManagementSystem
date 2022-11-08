package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePwdDto;
import com.library.librarymanagementsystem.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/updatePwd")
    public R updatePwd(@RequestBody UserUpdatePwdDto pwdDto){
        try {
            userService.updatePwd(pwdDto);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.ok();
    }
}
