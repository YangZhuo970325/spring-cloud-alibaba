package com.yangzhuo.seata.bank1.openfeign;

import org.springframework.stereotype.Component;

@Component
public class Bank2ClientFallback implements Bank2Client {

    @Override
    public String transfer(String userAccount, Double amount) {
        return "fallback";
    }
}
