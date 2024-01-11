package com.taffy.neko.manager;

import javax.servlet.http.HttpServletResponse;

public interface VerificationCodeGenerateManager {

    /**
     *
     * @return 随机六位验证码
     */
    String VerificationCode();

    /**
     * 登录图形验证码
     *
     * @param response 流
     */
    void getCode(HttpServletResponse response);
}
