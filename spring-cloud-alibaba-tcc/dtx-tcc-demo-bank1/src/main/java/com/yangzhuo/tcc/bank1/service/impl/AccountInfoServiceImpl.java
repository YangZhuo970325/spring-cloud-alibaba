package com.yangzhuo.tcc.bank1.service.impl;

import com.yangzhuo.tcc.bank1.dao.AccountInfoDao;
import com.yangzhuo.tcc.bank1.openfeign.Bank2Client;
import com.yangzhuo.tcc.bank1.service.AccountInfoService;
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

    @Autowired
    Bank2Client bank2Client;

    /**
     * @desc 账户扣款，就是tcc的try方法
     * 1、try幂等校验
     * 2、try悬挂处理
     * 3、检查余额是否够扣减金额
     * 4、扣减金额
     * @param accountNo
     * @param amount
     * @param toAccountNo
     */
    @Override
    //只要标记这个注解的就是try方法，在注解中要指定confirm、cancel两个方法名
    @Hmily(confirmMethod = "commit", cancelMethod = "rollback")
    @Transactional
    public void updateAccountBalance(String accountNo, Double amount, String toAccountNo) {
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank1 try begin.开始执行，xid:{}", transId);
        //幂等判断，判断local_try_log表中是否有try日志记录，如果有则不再执行
        if(accountInfoDao.isExistTry(transId) > 0) {
            log.info("bank1 try 已经执行，无须重复执行，xid:{}", transId);
            return;
        }

        //try 悬挂处理 confirm或cancel有一个已经执行，不允许执行try
        if(accountInfoDao.isExistConfirm(transId) > 0 || accountInfoDao.isExistCancel(transId) > 0) {
            log.info("bank1 try 悬挂处理 confirm或cancel已经执行，不允许执行try，xid:{}", transId);
            return;
        }

        //扣减金额
        if(accountInfoDao.subtractAccountBalance(accountNo, amount) <= 0){
            //扣减失败
            throw new RuntimeException("bank1 try 扣减金额失败，xid: " + transId);
        }

        //插入try执行记录，用于幂等判断
        accountInfoDao.addTry(transId);

        //远程调用李四转账
        if(!bank2Client.transfer(amount, toAccountNo)) {
            throw new RuntimeException("bank1 远程调用李四微服务失败，xid: " + transId);
        }

    }

    //confirm方法
    public void commit(String accountNo, Double amount, String toAccountNo) {
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank1 commit begin.开始执行，xid:{}", transId);
    }


    /**
     * @desc cancel方法
     * 1、cancel幂等校验
     * 2、cancel空回滚校验
     * 3、增加可用余额
     * @param accountNo
     * @param amount
     */
    @Transactional
    public void rollback(String accountNo, Double amount, String toAccountNo) {
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank1 cancel begin.开始执行，xid:{}", transId);
        // cancel幂等判断
        if(accountInfoDao.isExistCancel(transId) > 0) {
            log.info("bank1 cancel已经执行，无须重复执行，xid:{}", transId);
            return;
        }
        //cancel空回滚，如果try没有执行，cancel不允许执行
        if(accountInfoDao.isExistTry(transId) <= 0) {
            log.info("bank1 空回滚处理，try没有执行，不允许cancel执行，xid:{}", transId);
            return;
        }

        //增加可用金额
        accountInfoDao.addAccountBalance(accountNo, amount);
        //插入一条cancel执行记录
        accountInfoDao.addCancel(transId);
        log.info("bank1 cancel end.结束执行，xid:{}", transId);
    }
}
