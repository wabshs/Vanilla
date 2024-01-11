package com.taffy.neko.service;

import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
