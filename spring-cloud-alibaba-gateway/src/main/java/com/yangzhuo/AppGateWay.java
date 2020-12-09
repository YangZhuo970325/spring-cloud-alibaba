package com.yangzhuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yangzhuo.mapper")
public class AppGateWay {
    public static void main(String[] args) {
        SpringApplication.run(AppGateWay.class);
    }
}
