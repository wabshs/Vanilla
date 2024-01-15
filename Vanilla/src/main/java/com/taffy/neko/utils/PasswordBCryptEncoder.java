package com.taffy.neko.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 注册的时候加密密码用的
 */
@Component
public class PasswordBCryptEncoder {
    @Resource
    private PasswordEncoder passwordEncoder;

    public String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }
}
