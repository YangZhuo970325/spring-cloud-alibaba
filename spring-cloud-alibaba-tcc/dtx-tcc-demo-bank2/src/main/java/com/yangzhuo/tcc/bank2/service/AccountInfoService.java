package com.yangzhuo.tcc.bank2.service;

public interface AccountInfoService {
    //李四账户增加金额
    public void updateAccountBalance(Double amount, String toAccountNo);
}
