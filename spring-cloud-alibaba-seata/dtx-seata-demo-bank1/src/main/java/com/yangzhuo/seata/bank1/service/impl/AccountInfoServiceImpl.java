package com.yangzhuo.seata.bank1.service.impl;

import com.yangzhuo.seata.bank1.dao.AccountInfoDao;
import com.yangzhuo.seata.bank1.openfeign.Bank2Client;
import com.yangzhuo.seata.bank1.service.AccountInfoService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    Bank2Client bank2Client;

    @Transactional
    @GlobalTransactional //开启全局事务
    @Override
    public void updateAccountBalance(String accountNum, Double amount) {

        log.info("bank1 service begin,XID:{}", RootContext.getXID());
        //扣减张三的金额
        accountInfoDao.updateAccountBalance(accountNum, amount * -1);

        //调用李四微服务，转账
        String toAccount = "2";
        String transfer = bank2Client.transfer(toAccount, amount);

        if("fallback".equalsIgnoreCase(transfer)) {
            //调用李四微服务异常
            throw new RuntimeException("调用李四微服务异常");
        }

        if(amount == 2){
            throw new RuntimeException("bank1 make exception,XID:" + RootContext.getXID());
        }

    }
}
