package com.taffy.neko;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@MapperScan("com.taffy.neko.mapper")
@EnableEncryptableProperties
@Slf4j
public class NekoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NekoApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        String serviceName = environment.getProperty("spring.application.name");
        log.info("\n-----------------------------------------------------\n\t"
                + serviceName + " is running! 接口文档地址请查收:\n\t" + "localhost:" + port + "/doc.html" + "\n\t" +
                "前端地址:null,因为还没开发起来~\n\t" +
                "为什么我看起来难过悲伤，因为我是一只鸟儿却没在空中飞翔\n\t" +
                "你希望我随便飞又压着我的翅膀\n\t" +
                "就让我接着做梦谁都别叫我起床~\n\t" +
                "あなたが大好きだ\n\t"+
                "---------------------------------------------------------");

    }

}
