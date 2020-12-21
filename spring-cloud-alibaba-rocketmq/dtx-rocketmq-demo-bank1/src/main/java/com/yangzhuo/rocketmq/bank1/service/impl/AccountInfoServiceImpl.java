package com.yangzhuo.rocketmq.bank1.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yangzhuo.rocketmq.bank1.dao.AccountInfoDao;
import com.yangzhuo.rocketmq.bank1.model.AccountChangeEvent;
import com.yangzhuo.rocketmq.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    private static final String PRODUCER_GROUP = "producer_group_txmsg_bank1";

    private static final String PRODUCER_TOPIC = "topic_txmsg";

    //向mq发送转账消息
    @Override
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {

        //将accountChangeEvent转成json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountChange", accountChangeEvent);
        String jsonString = jsonObject.toJSONString();
        Message<String> message = MessageBuilder.withPayload(jsonString).build();

        //发送一条事务消息
        /**
         * String txProducerGroup 生产组
         * String destination topic
         * Message<?> message 消息内容
         * Object arg 参数
         */
        rocketMQTemplate.sendMessageInTransaction(PRODUCER_GROUP, PRODUCER_TOPIC, message, null);
    }

    //更新账户，扣减金额
    @Transactional
    @Override
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        //幂等判断
        if(accountInfoDao.isExistTx(accountChangeEvent.getTxNo()) > 0) {
            return;
        }
        //扣减金额
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(), accountChangeEvent.getAmount() * -1);
        //添加事务日志
        accountInfoDao.addTx(accountChangeEvent.getTxNo());
        if(accountChangeEvent.getAmount() == 3) {
            throw new RuntimeException("人为制造异常,bank1");
        }
    }
}
