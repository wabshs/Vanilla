package com.taffy.neko.controller;


import com.taffy.neko.manager.EmailManager;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class test {

    @Resource
    private EmailManager manager;

    @GetMapping("/a")
    public void test1() {
        manager.sendEmail("840654613@qq.com", "测试", "111");
    }

    @GetMapping("/b")
    public String test2() {
        manager.sendVerificationCodeEmail("1142572872@qq.com");
        return "hello world";
    }

    @GetMapping("/c")
    @PreAuthorize("hasAuthority('test')")
    public String test3() {
        return "hello world";
    }
}
