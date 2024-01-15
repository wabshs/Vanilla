package com.taffy.neko.controller;


import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(tags = "用户类")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResponseResult<?> userRegister() {
        return userService.userRegister();
    }
}
