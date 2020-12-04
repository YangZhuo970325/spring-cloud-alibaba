package com.yangzhuo.gmall.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RotationLoadBalancer implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance getSingleAddress(List<ServiceInstance> serviceInstanceList) {
        int index = atomicInteger.incrementAndGet()%serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
