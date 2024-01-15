package com.taffy.neko.entity.dto;

import lombok.Data;

@Data
public class UserRegisterReqDTO {

    //登录的用户名
    private String userName;

    //昵称
    private String nickName;

    //密码
    private String password;

    //邮箱
    private String email;

    //手机号
    private String phoneNumber;

    //性别 0女1男2未知
    private String sex;

    //头像
    private String avatar;


}
