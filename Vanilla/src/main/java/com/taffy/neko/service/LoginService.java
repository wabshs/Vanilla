package com.taffy.neko.service;

import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.LoginReqDTO;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    ResponseResult login(LoginReqDTO reqDTO);

    ResponseResult logout();

    /**
     * 生成图形验证码
     * @param response-流
     */
    void getCode(HttpServletResponse response);
}
