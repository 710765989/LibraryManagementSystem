package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.domian.dto.UserCreateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePwdDto;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.utils.Constant;
import com.library.librarymanagementsystem.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequestMapping("/user")
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

    @GetMapping("/list")
    public R getUserList(){
        return R.ok().put("data", userService.getUserList());
    }

    @GetMapping(value = {"/", "/{userId}"})
    public R getUser(@PathVariable(value = "userId", required = false) Integer userId){
        if (userId == null){
            return R.ok();
        }
        return R.ok().put("data", userService.getById(userId));
    }

    @PostMapping("/add")
    public R addUser(@RequestBody @Validated UserCreateDto createDto){
        userService.addUser(createDto);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateUser(@RequestBody @Validated UserUpdateDto updateDto){
        userService.updateUser(updateDto);
        return R.ok();
    }

    @Transactional
    @DeleteMapping("/{userId}")
    public R deleteUser(@PathVariable("userId") @NotNull(message = "用户ID不能为空") Integer userId){
        User user = userService.getById(userId);
        user.setDelFlag(Constant.YES);
        userService.updateById(user);
        return R.ok();
    }
}
