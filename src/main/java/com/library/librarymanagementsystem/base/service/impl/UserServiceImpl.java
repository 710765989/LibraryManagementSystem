package com.library.librarymanagementsystem.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.library.librarymanagementsystem.base.mapper.UserMapper;
import com.library.librarymanagementsystem.base.service.UserService;
import com.library.librarymanagementsystem.controller.LoginController;
import com.library.librarymanagementsystem.domian.dto.UserCreateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdateDto;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePwdDto;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return this.baseMapper.getByUsername(username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePwd(UserUpdatePwdDto pwdDto) {
        User user = ShiroUtils.getCurrentUser();
        if (user == null){
            throw new RuntimeException("用户数据获取失败");
        }
        try {
            if (!LoginController.getMD5(pwdDto.getOldPwd()).equals(user.getPassword())){
                throw new RuntimeException("旧密码有误");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (pwdDto.getOldPwd().equals(pwdDto.getNewPwd())){
            throw new RuntimeException("新旧密码不能相同");
        }
        if (!pwdDto.getRepeatPwd().equals(pwdDto.getNewPwd())){
            throw new RuntimeException("新密码与确认密码不一致");
        }
        try {
            user.setPassword(LoginController.getMD5(pwdDto.getNewPwd()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userMapper.updateById(user);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserCreateDto createDto) {
        // 用户名重复check
        if (userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, createDto.getUsername())) > 0){
            throw new RuntimeException(String.format("用户名【%s】已存在", createDto.getUsername()));
        }
        User user = new User();
        user.setUsername(createDto.getUsername());
        try {
            user.setPassword(LoginController.getMD5(createDto.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setType(createDto.getType());
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserUpdateDto updateDto) {
        User user = userMapper.selectById(updateDto.getId());
        if (user == null){
            throw new RuntimeException("用户数据不存在");
        }
        // 修改用户名判断
        if (!user.getUsername().equals(updateDto.getUsername())){
            if (userMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, updateDto.getUsername())) > 0){
                throw new RuntimeException(String.format("用户名【%s】已存在", updateDto.getUsername()));
            }
        }
        user.setUsername(updateDto.getUsername());
        user.setType(updateDto.getType());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }
}
