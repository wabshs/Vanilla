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

}
