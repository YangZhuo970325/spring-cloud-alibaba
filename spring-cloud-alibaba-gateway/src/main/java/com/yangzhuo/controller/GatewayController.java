package com.yangzhuo.controller;

import com.yangzhuo.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    /**
     * 同步网关配置
     *
     * @return
     */
    @RequestMapping("/syncGatewayConfig")
    public String syncGatewayConfig() {
        return gatewayService.loadRoute();
    }
}
