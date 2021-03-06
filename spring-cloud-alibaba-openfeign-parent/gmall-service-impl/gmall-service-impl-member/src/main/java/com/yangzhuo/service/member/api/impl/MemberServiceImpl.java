package com.yangzhuo.service.member.api.impl;

import com.yangzhuo.service.member.api.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
public class MemberServiceImpl implements MemberService {

    @Value("${server.port}")
    private String serverPort;

    @Value("${gmall.name}")
    private String gmallName;

    @Override
    public String getUser(int Id) {
        return "人民币玩家：" + Id + "，端口号：" + serverPort + "商城名称：" + gmallName;
    }

    @RequestMapping("/getGatewayPort")
    public String getGatewayPort(HttpServletRequest request) {
        String serverPort = request.getHeader("serverPort");
        return "this is member , 网关端口号为：" + serverPort;
    }
}
