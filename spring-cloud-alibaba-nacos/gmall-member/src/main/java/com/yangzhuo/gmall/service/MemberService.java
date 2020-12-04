package com.yangzhuo.gmall.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberService {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 会员服务提供一个接口
     * @param Id
     * @return
     */
    @GetMapping("/getUser")
    public String getUser(long Id) {
        return "人民币玩家 : " +Id + " ,端口号：" + serverPort;
    }
}
