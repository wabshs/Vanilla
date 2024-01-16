package com.taffy.neko.manager.impl;

import com.taffy.neko.manager.EmailManager;
import com.taffy.neko.manager.VerificationCodeGenerateManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailManagerImpl implements EmailManager {

    @Resource
    private JavaMailSender sender;


    @Value("${spring.mail.from}")
    private String from;

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //收件人
        message.setTo(to);
        //主题
        message.setSubject(subject);
        //内容
        message.setText(content);
        //发送
        sender.send(message);
    }

    @Override
    public void sendVerificationCodeEmail(String to,String emailCode) {
        String subject = "Neko";
        String content = "【智慧校园】您好，您的验证码:" + emailCode + "。 五分钟内有效，请勿泄露。";
        sendEmail(to, subject, content);
    }

}
