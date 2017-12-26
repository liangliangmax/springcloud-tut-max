package com.example.userprovider.service;

import com.example.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "micro-service",fallback = UserServiceHystrix.class)
public interface IUserService {

    @RequestMapping(value="/api/student/{id}",method = RequestMethod.GET)
    String findById(@RequestParam("id") String id);

    @RequestMapping(value = "/api/student/username/{username}",method = RequestMethod.GET)
    String findByUsername(@RequestParam("username") String username);

    @RequestMapping(value = "/api/student/all",method = RequestMethod.GET)
    String findALl();

    @RequestMapping(value = "/api/student/add",method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
    int add(@RequestBody User user);

    @RequestMapping(value = "/api/student/page",method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
    String findByPage(@RequestBody Map<String,Object> params);

}
