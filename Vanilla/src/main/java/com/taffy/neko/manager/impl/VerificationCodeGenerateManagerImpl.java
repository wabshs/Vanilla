package com.taffy.neko.manager.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.RandomUtil;
import com.taffy.neko.manager.VerificationCodeGenerateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class VerificationCodeGenerateManagerImpl implements VerificationCodeGenerateManager {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String VerificationCode() {
        return RandomUtil.randomNumbers(6);
    }

    @Override
    public void getCode(HttpServletResponse response) {
        //随机生成四位数验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        //定义图片的显示大小
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        try {
            //调用父类的setGenerator方法
            lineCaptcha.setGenerator(randomGenerator);
            lineCaptcha.write(response.getOutputStream());
            logger.info("生成的验证码:{}", lineCaptcha.getCode());
            //关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
