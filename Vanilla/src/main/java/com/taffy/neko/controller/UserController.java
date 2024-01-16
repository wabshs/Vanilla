package com.taffy.neko.controller;


import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.entity.dto.LoginReqDTO;
import com.taffy.neko.entity.dto.UserRegisterReqDTO;
import com.taffy.neko.service.LoginService;
import com.taffy.neko.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@Api(tags = "用户类")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private LoginService loginService;


    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResponseResult<?> userRegister(@RequestBody UserRegisterReqDTO reqDTO) {
        return userService.userRegister(reqDTO);
    }


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody LoginReqDTO reqDTO) {
        //登录
        return loginService.login(reqDTO);
    }

    @PostMapping("/logout")
    @ApiOperation("用户登出")
    public ResponseResult logout() {
        //登出
        return loginService.logout();
    }

    @GetMapping("/getCode")
    @ApiOperation("图片验证码")
    public void getCode(HttpServletResponse response) {
        loginService.getCode(response);
    }

    @PostMapping("/sendEmailCode")
    @ApiOperation("发送注册验证码")
    public ResponseResult<?> sendEmailCode(@RequestParam String email){
        return userService.sendEmailCode(email);
    }
}
