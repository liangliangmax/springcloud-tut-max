package com.example.zuul.rpc;

import com.example.entity.UserForAuth;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-provider")
public interface UserRpc {

    @RequestMapping(value = "/student/user/validate",method = RequestMethod.POST)
    UserForAuth login(@RequestParam("username") String username, @RequestParam("password") String password);
}
