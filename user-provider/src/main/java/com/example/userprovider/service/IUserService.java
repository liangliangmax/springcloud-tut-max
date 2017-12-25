package com.example.userprovider.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "micro-service",fallback = UserServiceHystrix.class)
public interface IUserService {

    @RequestMapping(value="/api/student/user/{id}/{username}",method = RequestMethod.GET)
    String findById(@RequestParam("id") String id,@RequestParam("username") String username);


}
