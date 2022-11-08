package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.base.service.UserService;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePwdDto;
import com.library.librarymanagementsystem.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/updatePwd")
    public R updatePwd(UserUpdatePwdDto pwdDto){
        userService.updatePwd(pwdDto);
        return R.ok();
    }
}
