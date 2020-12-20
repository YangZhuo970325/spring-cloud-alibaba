package com.yangzhuo.seata.bank2.service.impl;

import com.yangzhuo.seata.bank2.dao.AccountInfoDao;
import com.yangzhuo.seata.bank2.entity.AccountInfo;
import com.yangzhuo.seata.bank2.service.AccountInfoService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    @Override
    public void updateAccountBalance(String accountNum, Double amount) {
        String accountName = accountInfoDao.queryAccountName(accountNum);
        if(accountName == null){
            throw new RuntimeException("accountNum is null,XID:" + RootContext.getXID());
        }
        log.info("bank2 service begin,XID:{}", RootContext.getXID());
        //李四增加金额
        if(accountInfoDao.updateAccountBalance(accountNum, amount) < 1) {
            //李四增加金额失败
            throw new RuntimeException("bank2 update money exception, XID:" + RootContext.getXID());
        }
        if(amount == 3) {
            //人为制造异常
            throw new RuntimeException("bank2 make exception,XID:" + RootContext.getXID());
        }
    }
}
