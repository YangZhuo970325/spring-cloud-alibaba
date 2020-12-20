package com.yangzhuo.tcc.bank2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.yangzhuo.tcc.bank2", "org.dromara.hmily"})
public class Bank2TccServer {
    public static void main(String[] args) {
        SpringApplication.run(Bank2TccServer.class);
    }
}