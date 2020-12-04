package com.yangzhuo.service.member.api;

import org.springframework.web.bind.annotation.GetMapping;

public interface MemberService {

    @GetMapping("/getUser")
    String getUser(int Id);
}
