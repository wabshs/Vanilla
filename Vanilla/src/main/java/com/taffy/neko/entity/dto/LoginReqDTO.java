package com.taffy.neko.entity.dto;


import lombok.Data;

@Data
public class LoginReqDTO {
    //用户名
    private String userName;
    //密码
    private String password;
    //验证码
    private String code;
}
