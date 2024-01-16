package com.taffy.neko.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.ResponseResult;
import com.taffy.neko.convert.UserConvert;
import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.UserRegisterReqDTO;

import com.taffy.neko.manager.EmailManager;
import com.taffy.neko.manager.VerificationCodeGenerateManager;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.service.UserService;
import com.taffy.neko.utils.PasswordBCryptEncoder;
import com.taffy.neko.utils.RedisCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordBCryptEncoder bCryptEncoder;

    //redis工具类
    @Resource
    private RedisCache redisCache;

    @Resource
    private EmailManager emailManager;

    @Resource
    private VerificationCodeGenerateManager verificationCodeGenerateManager;


    @Override
    public ResponseResult<?> userRegister(UserRegisterReqDTO reqDTO) {
        String password = reqDTO.getPassword();//明文密码
        String encodedPwd = bCryptEncoder.passwordEncode(password);
        String emailCode = reqDTO.getEmailCode();//用户输入的邮箱验证码
        String emailCodeInRedis = redisCache.getCacheObject(reqDTO.getEmail());
        reqDTO.setPassword(encodedPwd); //再存进去加密的密码
        if (Objects.equals(emailCode, emailCodeInRedis)) {
            //转化为User对象
            User user = UserConvert.INSTANCE.toUser(reqDTO);
            userMapper.insert(user);
            redisCache.deleteObject(reqDTO.getEmail());//注册完之后即删除缓存的验证码
            return new ResponseResult<>(200, "注册成功");
        } else {
            return new ResponseResult<>(500, "注册失败,请检查验证码是否有误~");
        }
    }

    @Override
    public ResponseResult<?> sendEmailCode(String email) {
        //邮箱验证码
        String emailCode = verificationCodeGenerateManager.VerificationCode(); //邮箱验证码
        //存到Redis里面 key是email Value是验证码 五分钟过期
        redisCache.setCacheObject(email, emailCode, 5, TimeUnit.MINUTES);
        emailManager.sendVerificationCodeEmail(email, emailCode);
        return new ResponseResult<>(200, "发送成功");
    }
}
