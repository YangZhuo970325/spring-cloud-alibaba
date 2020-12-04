package com.yangzhuo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class AppOrder
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppOrder.class);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 加上@LoadBalanced注解就可以实现本地负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate1(){
        return new RestTemplate();
    }

}
