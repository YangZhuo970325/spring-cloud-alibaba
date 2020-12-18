package com.yangzhuo.seata.bank1.service;

public interface AccountInfoService {

    //扣减金额
    public void updateAccountBalance(String accountNum, Double amount);
}
