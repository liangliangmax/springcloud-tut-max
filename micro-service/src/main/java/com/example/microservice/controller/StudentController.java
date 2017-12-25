package com.example.microservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.microservice.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @RequestMapping("/user/{id}/{username}")
    public String findById(@PathVariable("id")  String id,@PathVariable("username") String username){

        User user1=new User();

        user1.setId(id);
        user1.setUsername(username);
        user1.setPassword("123456");

        return JSONObject.toJSONString(user1);



    }
}
