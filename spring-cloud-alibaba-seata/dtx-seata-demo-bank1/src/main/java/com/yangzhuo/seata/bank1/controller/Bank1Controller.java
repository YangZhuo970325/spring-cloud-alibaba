package com.yangzhuo.seata.bank1.controller;

import com.yangzhuo.seata.bank1.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bank1Controller {

    @Autowired
    AccountInfoService accountInfoService;

    //张三转账
    @GetMapping("/transfer")
    public String transfer(String userAccount, Double amount) {
        accountInfoService.updateAccountBalance(userAccount, amount);
        return "bank1" + amount;
    }

}
