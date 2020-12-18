package com.yangzhuo.seata.bank2.service;

public interface AccountInfoService {

    //增加金额
    public void updateAccountBalance(String accountNum, Double amount);
}
