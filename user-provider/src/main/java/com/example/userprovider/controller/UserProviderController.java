package com.example.userprovider.controller;

import com.example.entity.User;
import com.example.userprovider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class UserProviderController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/{id}")
    public String findById(@PathVariable("id") String id){

        return userService.findById(id);
    }

    @RequestMapping("/username/{username}")
    public String findByUsername(@PathVariable("username") String username){

        return userService.findByUsername(username);
    }

    @RequestMapping("/all")
    public String findAll(){
        return userService.findALl();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int add(User user){
        return userService.add(user);

    }
}
