package com.taffy.neko.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.taffy.neko.Result.LoginUser;
import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.LoginReqDTO;
import com.taffy.neko.service.LoginService;
import com.taffy.neko.utils.JwtUtil;
import com.taffy.neko.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    private LineCaptcha lineCaptcha;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;


    @Override
    public void getCode(HttpServletResponse response) {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30); //设置验证码尺寸大小
        response.setContentType("image/jpeg"); //设置类型
        response.setHeader("Pragma", "No-cache");
        try {
            lineCaptcha.setGenerator(randomGenerator);
            lineCaptcha.write(response.getOutputStream()); //输出到页面
            log.info("生成的验证码为{}", lineCaptcha.getCode());
            //关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseResult login(LoginReqDTO reqDTO) {
        User user = new User();
        user.setUserName(reqDTO.getUserName());
        user.setPassword(reqDTO.getPassword());
        String code = reqDTO.getCode();
        //authenticate进行用户认证 把用户名和密码封装成UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //调用的是UserDetailServiceImpl的方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证没通过，给出对应的提示(认证失败||验证码不对)
        if (Objects.isNull(authenticate) || !Objects.equals(code, lineCaptcha.getCode())) {
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
