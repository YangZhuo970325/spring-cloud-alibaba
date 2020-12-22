package com.yangzhuo.notifymsg.bank1.service;

import com.yangzhuo.notifymsg.bank1.entity.AccountPay;
import com.yangzhuo.notifymsg.bank1.model.AccountChangeEvent;

/**
 * Created by Administrator.
 */
public interface AccountInfoService {

    //更新账户金额
    public void updateAccountBalance(AccountChangeEvent accountChange);

    //查询充值结果（远程调用）
    public AccountPay queryPayResult(String tx_no);

}
