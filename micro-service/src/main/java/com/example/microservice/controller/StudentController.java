package com.example.microservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.microservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/student/{id}")
    public String findById(@PathVariable("id")  String id){

        System.out.println("开始查询");

        User user= userService.findById(id);

        return JSONObject.toJSONString(user);

    }

    @RequestMapping("/student/username/{username}")
    public String findByUsername(@PathVariable("username") String username){

        User user=userService.selectByUsername(username);

        return JSONObject.toJSONString(user);

    }

    @RequestMapping("/student/all")
    public String findAll(){

        List<User> list = userService.findAll();

        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value = "/student/add",method = RequestMethod.POST)
    public int add(@RequestBody User user){

        return userService.add(user);
    }
}
