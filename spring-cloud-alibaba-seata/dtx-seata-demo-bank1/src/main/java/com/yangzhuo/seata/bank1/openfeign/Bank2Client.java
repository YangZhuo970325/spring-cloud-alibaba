package com.yangzhuo.seata.bank1.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-demo-bank2", fallback = Bank2ClientFallback.class)
public interface Bank2Client {

    //远程调用李四的微服务
    @GetMapping("/bank2/transfer")
    public String transfer(@RequestParam("userAccount") String userAccount, @RequestParam("amount") Double amount);

}
