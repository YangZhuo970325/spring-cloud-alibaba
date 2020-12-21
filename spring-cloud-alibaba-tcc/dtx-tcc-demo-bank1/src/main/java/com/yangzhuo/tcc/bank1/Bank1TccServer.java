package com.yangzhuo.tcc.bank1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "com.yangzhuo.tcc.bank1.openfeign")
@ComponentScan({"com.yangzhuo.tcc.bank1", "org.dromara.hmily"})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Bank1TccServer {
    public static void main(String[] args) {
        SpringApplication.run(Bank1TccServer.class);
    }
}
