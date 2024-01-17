package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.UserRegisterReqDTO;

public interface UserService extends IService<User> {


    /**
     * 用户注册接口
     * @param reqDTO 注册请求体
     * @return R
     */
    ResponseResult<?> userRegister(UserRegisterReqDTO reqDTO);

    /**
     * 注册的时候发送邮箱验证码
     * @param email-邮箱地址
     * @return 通用返回类
     */
    ResponseResult<?> sendEmailCode(String email);
}
