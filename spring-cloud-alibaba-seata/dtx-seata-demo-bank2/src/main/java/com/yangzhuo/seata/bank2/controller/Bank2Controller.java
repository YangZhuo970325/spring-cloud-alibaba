package com.yangzhuo.seata.bank2.controller;

import com.yangzhuo.seata.bank2.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bank2Controller {

    @Autowired
    AccountInfoService accountInfoService;

    //接收张三转账
    @GetMapping("/transfer")
    public String transfer(String userAccount, Double amount) {
        //李四
        accountInfoService.updateAccountBalance(userAccount, amount);
        return "bank2" + amount;
    }

}
