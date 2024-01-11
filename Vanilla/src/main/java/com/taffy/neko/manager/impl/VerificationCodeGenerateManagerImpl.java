package com.taffy.neko.manager.impl;

import cn.hutool.core.util.RandomUtil;
import com.taffy.neko.manager.VerificationCodeGenerateManager;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeGenerateManagerImpl implements VerificationCodeGenerateManager {
    @Override
    public String VerificationCode() {
        return RandomUtil.randomNumbers(6);
    }
}
