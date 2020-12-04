package com.yangzhuo.service.order.impl;

import com.yangzhuo.service.order.openfeign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    /**
     * 基于feign客户端实现rpc远程调用
     * @return
     */
    @RequestMapping("/orderFeignToMember")
    public String orderFeignToMember() {
        String result = memberServiceFeign.getUser(10);
        return "订单服务：" + result;
    }
}
