package com.yangzhuo.rocketmq.bank1.controller;

import com.yangzhuo.rocketmq.bank1.model.AccountChangeEvent;
import com.yangzhuo.rocketmq.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @GetMapping(value = "/transfer")
    public String transfer(@RequestParam("accountNo") String accountNo, @RequestParam("amount") Double amount) {
        String tx_no = UUID.randomUUID().toString();
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent(accountNo, amount, tx_no);

        accountInfoService.sendUpdateAccountBalance(accountChangeEvent);
        return "转账成功";

    }
}
