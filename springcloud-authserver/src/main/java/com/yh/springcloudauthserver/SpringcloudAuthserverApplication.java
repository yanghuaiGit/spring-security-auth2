package com.yh.springcloudauthserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yh.springcloudauthserver.dao")
public class SpringcloudAuthserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudAuthserverApplication.class, args);
    }

}

