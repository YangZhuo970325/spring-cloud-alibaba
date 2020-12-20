package com.yangzhuo.tcc.bank2.service.impl;

import com.yangzhuo.tcc.bank2.dao.AccountInfoDao;
import com.yangzhuo.tcc.bank2.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    AccountInfoDao accountInfoDao;


    /**
     * @desc 账户扣款，就是tcc的try方法
     * 1、try幂等校验
     * 2、try悬挂处理
     * 3、检查余额是否够扣减金额
     * 4、扣减金额
     * @param amount
     * @param toAccountNo
     */
    @Override
    //只要标记这个注解的就是try方法，在注解中要指定confirm、cancel两个方法名
    @Hmily(confirmMethod = "commit", cancelMethod = "rollback")
    @Transactional
    public void updateAccountBalance(Double amount, String toAccountNo) {
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank2 commit begin.开始执行，xid:{}", transId);

    }

    //confirm方法
    @Transactional
    public void commit(Double amount, String toAccountNo) {
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank2 commit begin.开始执行，xid:{}", transId);
        if(accountInfoDao.isExistConfirm(transId) > 0) {
            log.info("bank2 confirm 已经执行，无须重复执行...xid:{}", transId);
            return;
        }
        //增加金额
        accountInfoDao.addAccountBalance(toAccountNo, amount);
        //增加一条confirm日志，用于幂等
        accountInfoDao.addConfirm(transId);
    }


    /**
     * @desc 正式增加金额
     * 1、cancel幂等校验
     * 2、cancel空回滚校验
     * 3、增加可用余额
     * @param toAccountNo
     * @param amount
     */
    public void rollback(Double amount, String toAccountNo) {
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank2 cancel begin.开始执行，xid:{}", transId);

    }
}
