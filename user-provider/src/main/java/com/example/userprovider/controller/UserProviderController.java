package com.example.userprovider.controller;

import com.example.userprovider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserProviderController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/{id}/{username}")
    public String findById(@PathVariable("id") String id,@PathVariable("username") String username){

        return userService.findById(id,username);
    }
}
