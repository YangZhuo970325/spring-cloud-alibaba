package com.yangzhuo.gmall.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public class IpHashLoadBalancer implements LoadBalancer {
    @Override
    public ServiceInstance getSingleAddress(List<ServiceInstance> serviceInstanceList) {
        return null;
    }
}
