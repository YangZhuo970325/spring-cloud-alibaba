package com.yangzhuo.rocketmq.bank2.message;

import com.alibaba.fastjson.JSONObject;
import com.yangzhuo.rocketmq.bank2.dao.AccountInfoDao;
import com.yangzhuo.rocketmq.bank2.model.AccountChangeEvent;
import com.yangzhuo.rocketmq.bank2.service.AccountInfoService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "producer_group_txmsg_bank2", topic = "topic_txmsg")
public class TxmsgConsumer implements RocketMQListener<String> {

    @Autowired
    AccountInfoService accountInfoService;

    @Autowired
    AccountInfoDao accountInfoDao;

    @Override
    public void onMessage(String s) {

        //解析消息
        JSONObject jsonObject = JSONObject.parseObject(s);
        String accountChangeString = jsonObject.getString("accountChange");
        //json转AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);

        accountChangeEvent.setAccountNo("2");
        //更新本地账户，增加金额
        accountInfoService.addAccountInfoBalance(accountChangeEvent);

    }
}
