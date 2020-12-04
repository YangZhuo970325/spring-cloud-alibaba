package com.yangzhuo.gmall.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    /**
     * 从注册中心集群列表中拿到单个地址
     * @param serviceInstanceList
     * @return
     */
    ServiceInstance getSingleAddress(List<ServiceInstance> serviceInstanceList);
}
