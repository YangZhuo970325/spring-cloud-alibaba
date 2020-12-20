package com.yangzhuo.tcc.bank1.controller;


import com.yangzhuo.tcc.bank1.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class Bank1Controller {
    @Autowired
    AccountInfoService accountInfoService;

    @RequestMapping("/transfer")
    public Boolean transfer(@RequestParam("accountNo") String accountNo,
                            @RequestParam("amount") Double amount, @RequestParam("toAccountNo") String toAccountNo) {
        this.accountInfoService.updateAccountBalance(accountNo, amount, toAccountNo);
        return true;
    }

}
