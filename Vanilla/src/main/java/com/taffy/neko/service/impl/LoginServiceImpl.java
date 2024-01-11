package com.taffy.neko.service.impl;

import com.taffy.neko.Result.LoginUser;
import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.entity.User;
import com.taffy.neko.service.LoginService;
import com.taffy.neko.utils.JwtUtil;
import com.taffy.neko.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //authenticate进行用户认证 把用户名和密码封装成UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //调用的是UserDetailServiceImpl的方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //认证通过就拿UserID生成一个jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //把完整的用户信息存入redis，userID作为Key
        redisCache.setCacheObject("login:" + userId, loginUser);
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户ID
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
//        删除redis里的值
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult(200, "注销成功");
    }
}
