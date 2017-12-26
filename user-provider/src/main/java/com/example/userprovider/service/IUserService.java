package com.example.userprovider.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "micro-service",fallback = UserServiceHystrix.class)
public interface IUserService {

    @RequestMapping(value="/api/student/{id}",method = RequestMethod.GET)
    String findById(@RequestParam("id") String id);

    @RequestMapping(value = "/api/student/username/{username}",method = RequestMethod.GET)
    String findByUsername(@RequestParam("username") String username);

    @RequestMapping(value = "/api/student/all",method = RequestMethod.GET)
    String findALl();

}
