package com.taffy.neko.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.convert.UserConvert;
import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.UserRegisterReqDTO;

import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.service.UserService;
import com.taffy.neko.utils.PasswordBCryptEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordBCryptEncoder bCryptEncoder;


    @Override
    public ResponseResult<?> userRegister(UserRegisterReqDTO reqDTO) {
        String password = reqDTO.getPassword();//明文密码
        String encodedPwd = bCryptEncoder.passwordEncode(password);
        reqDTO.setPassword(encodedPwd); //再存进去
        //转化为User对象
        User user = UserConvert.INSTANCE.toUser(reqDTO);
        int ifSuccess = userMapper.insert(user);//插入数据库
        if (ifSuccess == 1) {
            return new ResponseResult<>(200, "注册成功");
        } else {
            return new ResponseResult<>(500, "注册失败");
        }
    }
}
