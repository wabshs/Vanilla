package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.UserRegisterReqDTO;

public interface UserService extends IService<User> {


    ResponseResult<?> userRegister(UserRegisterReqDTO reqDTO);

    ResponseResult<?> sendEmailCode(String email);
}
