package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.domian.dto.UserCreateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePasswordDto;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.utils.Constant;
import com.library.librarymanagementsystem.utils.R;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    /**
     * 修改用户密码
     */
    @PostMapping("/update/password")
    public R updatePassword(@RequestBody UserUpdatePasswordDto dto){
        try {
            userService.updatePassword(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.ok();
    }

    /**
     * 用户一览
     */
    @GetMapping("/list")
    public R getUserList(){
        return R.ok().put("data", userService.getUserList());
    }

    /**
     * 获取用户信息
     */
    @GetMapping(value = {"/", "/{userId}"})
    public R getUser(@PathVariable(value = "userId", required = false) Integer userId){
        if (userId == null){
            return R.ok();
        }
        return R.ok().put("data", userService.getById(userId));
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    public R addUser(@RequestBody @Validated UserCreateDto createDto){
        userService.addUser(createDto);
        return R.ok();
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/update")
    public R updateUser(@RequestBody @Validated UserUpdateDto updateDto){
        userService.updateUser(updateDto);
        return R.ok();
    }

    /**
     * 删除用户
     */
    @Transactional
    @DeleteMapping("/{userId}")
    public R deleteUser(@PathVariable("userId") @NotNull(message = "用户ID不能为空") Integer userId){
        User user = userService.getById(userId);
        user.setDelFlag(Constant.YES);
        userService.updateById(user);
        return R.ok();
    }
}
