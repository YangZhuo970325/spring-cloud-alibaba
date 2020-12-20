package com.yangzhuo.tcc.bank1.service;

public interface AccountInfoService {

    //张三账户扣减金额
    public void updateAccountBalance(String accountNo, Double amount, String toAccountNo);
}
