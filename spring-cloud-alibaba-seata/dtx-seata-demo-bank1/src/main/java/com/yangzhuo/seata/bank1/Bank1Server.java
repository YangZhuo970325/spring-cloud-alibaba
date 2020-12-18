package com.yangzhuo.seata.bank1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.yangzhuo.seata.bank1.openfeign"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Bank1Server {
    public static void main(String[] args) {
        SpringApplication.run(Bank1Server.class);
    }
}
