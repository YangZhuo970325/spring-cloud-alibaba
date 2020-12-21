package com.yangzhuo.rocketmq.bank1.service;

import com.yangzhuo.rocketmq.bank1.model.AccountChangeEvent;

public interface AccountInfoService {

    //向mq发送转账消息
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent);

    //更新账户余额，扣减金额
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent);
}
