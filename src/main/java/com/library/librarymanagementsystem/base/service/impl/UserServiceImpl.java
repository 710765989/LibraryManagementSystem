package com.library.librarymanagementsystem.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.library.librarymanagementsystem.base.mapper.UserMapper;
import com.library.librarymanagementsystem.base.service.UserService;
import com.library.librarymanagementsystem.controller.LoginController;
import com.library.librarymanagementsystem.domian.dto.UserUpdatePwdDto;
import com.library.librarymanagementsystem.entity.User;
import com.library.librarymanagementsystem.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return this.baseMapper.getByUsername(username);
    }

    @Transactional
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
}
