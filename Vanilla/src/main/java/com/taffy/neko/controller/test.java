package com.taffy.neko.controller;


import com.taffy.neko.manager.EmailManager;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "测试模块")
public class test {

    @Resource
    private EmailManager manager;

    @GetMapping("/a")
    public void test1() {
        manager.sendEmail("840654613@qq.com", "测试", "111");
    }


    @GetMapping("/c")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String test3() {
        return "hello world";
    }
}
