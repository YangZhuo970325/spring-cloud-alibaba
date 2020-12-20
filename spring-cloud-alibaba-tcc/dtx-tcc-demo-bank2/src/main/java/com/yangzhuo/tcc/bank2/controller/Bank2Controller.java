package com.yangzhuo.tcc.bank2.controller;

import com.yangzhuo.tcc.bank2.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bank2Controller {

    @Autowired
    AccountInfoService accountInfoService;

    @RequestMapping("/transfer")
    public Boolean transfer(@RequestParam("amount") Double amount, @RequestParam("toAccountNo") String toAccountNo) {
        accountInfoService.updateAccountBalance(amount, toAccountNo);
        return true;
    }
}
