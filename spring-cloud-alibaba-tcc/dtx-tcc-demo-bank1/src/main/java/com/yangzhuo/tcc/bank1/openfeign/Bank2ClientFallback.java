package com.yangzhuo.tcc.bank1.openfeign;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 **/
@Component
public class Bank2ClientFallback implements Bank2Client {

    @Override
    public Boolean transfer(Double amount, String toAccountNo) {

        return false;
    }
}
