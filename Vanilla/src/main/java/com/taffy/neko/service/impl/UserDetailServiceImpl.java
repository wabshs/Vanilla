package com.taffy.neko.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taffy.neko.Result.LoginUser;
import com.taffy.neko.entity.User;
import com.taffy.neko.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 实现SpringSecurity自带的接口，重写它的官方方法替换成我自己的
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }


        //todo 查询授权信息

        //封装成UserDetails返回
        return new LoginUser(user);
    }
}
