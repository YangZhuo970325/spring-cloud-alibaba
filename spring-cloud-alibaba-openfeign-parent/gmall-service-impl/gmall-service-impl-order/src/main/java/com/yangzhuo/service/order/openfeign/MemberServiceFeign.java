package com.yangzhuo.service.order.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gmall-member")
public interface MemberServiceFeign {

    @GetMapping("getUser")
    String getUser(@RequestParam("Id") int Id);
}
