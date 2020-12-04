package com.yangzhuo.gmall.service;

import com.yangzhuo.gmall.loadbalancer.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplate1;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 订单服务调用会员服务接口
     * @return
     */
    @RequestMapping("/orderToMember")
    public String orderToMember() {
        // 1. 根据服务名称从 注册中心获取集群列表地址
        List<ServiceInstance> instances = discoveryClient.getInstances("gmall-member");
        // 2. 列表任意选择一个 实现本地rpc调用
        ServiceInstance serviceInstance = loadBalancer.getSingleAddress(instances);
        URI memberUrl = serviceInstance.getUri();
        String result = restTemplate.getForObject(memberUrl + "/getUser?Id=10", String.class);
        return "订单调用会员返回结果：" + result;
    }

    /**
     * 使用@LoadBalanced注解实现负载均衡
     * @return
     */
    @RequestMapping("/orderToRibbonMember")
    public String orderToRibbonMember() {
        String result = restTemplate1.getForObject( "http://gmall-member/getUser?Id=10", String.class);
        return "订单调用会员返回结果：" + result;
    }

    /**
     * 使用loadBalancerClient实现负载均衡
     * @return
     */
    @RequestMapping("/orderToLoadBalancerClientMember")
    public String orderToLoadBalancerClientMember() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("gmall-member");
        URI memberUrl = serviceInstance.getUri();
        String result = restTemplate.getForObject(memberUrl + "/getUser?Id=10", String.class);
        return result;
    }
}
