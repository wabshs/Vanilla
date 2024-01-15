package com.taffy.neko.controller;

import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.LoginReqDTO;
import com.taffy.neko.manager.VerificationCodeGenerateManager;
import com.taffy.neko.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody LoginReqDTO reqDTO) {
        //登录
        return loginService.login(reqDTO);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        //登出
        return loginService.logout();
    }

    @GetMapping("/login/getCode")
    public void getCode(HttpServletResponse response) {
        loginService.getCode(response);
    }
}
