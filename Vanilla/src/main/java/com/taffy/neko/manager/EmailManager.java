package com.taffy.neko.manager;


public interface EmailManager {

    /**
     * @param to      收件人
     * @param subject 主题
     * @param content 正文
     */
    void sendEmail(String to, String subject, String content);

    /**
     * @param to 收件人
     */
    void sendVerificationCodeEmail(String to, String emailCode);
}
